package io.github.dumijdev.restdb4j.server.adapters.output.database;

import io.github.dumijdev.restdb4j.server.adapters.output.database.utils.SQLGenerator;
import io.github.dumijdev.restdb4j.server.application.core.domain.common.Where;
import io.github.dumijdev.restdb4j.server.application.core.domain.exceptions.InvalidException;
import io.github.dumijdev.restdb4j.server.application.core.domain.exceptions.ValidationException;
import io.github.dumijdev.restdb4j.server.application.core.domain.select.SelectItem;
import io.github.dumijdev.restdb4j.server.application.core.domain.select.SelectParams;
import io.github.dumijdev.restdb4j.server.application.core.domain.select.SelectResult;
import io.github.dumijdev.restdb4j.server.application.ports.output.SelectOperationOutputPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.List;

import static io.github.dumijdev.restdb4j.server.application.core.domain.common.Where.Operator.NOT_NULL;
import static io.github.dumijdev.restdb4j.server.application.core.domain.common.Where.Operator.NULL;

@Slf4j(topic = "SelectOperation")
@Component
public class SelectOperationSpringDBAdapter implements SelectOperationOutputPort {
  private final JdbcClient client;
  private final SQLGenerator sqlGenerator;

  public SelectOperationSpringDBAdapter(JdbcClient client, SQLGenerator sqlGenerator) {
    this.client = client;
    this.sqlGenerator = sqlGenerator;
  }

  @Override
  public SelectResult select(SelectParams params) {
    try {
      var isCustomSqlQuery = params.sqlQuery().isPresent();
      var sqlString = isCustomSqlQuery ? "SELECT %s".formatted(params.sqlQuery().get()) : sqlGenerator.generateSelect(params);

      log.info("Select SQL: {}", sqlString);
      var sql = client.sql(sqlString);

      if (!isCustomSqlQuery && params.where().isPresent()) {
        params.where().get().conditions()
            .forEach(condition -> isNotReference(condition.operator(), sql, condition.value()));
      }

      if (!isCustomSqlQuery && (params.joins() != null && !params.joins().isEmpty())) {
        for (var join : params.joins()) {
          for (var onItem : join.on()) {
            onItem.value().ifPresent(sql::param);
          }
        }
      }

      var results = sql.query(selectItemAdapter(params)).list();

      return new SelectResult(results);
    } catch (EmptyResultDataAccessException e) {
      log.info("No results found");
      return SelectResult.empty();
    } catch (BadSqlGrammarException e) {
      log.error("Bad SQL grammar exception", e);
      throw new InvalidException(List.of(new ValidationException.ItemException("internal", e.getMessage())));
    } catch (InvalidDataAccessApiUsageException e) {
      log.error("Invalid data access api usage", e);
      throw new InvalidException(List.of(new ValidationException.ItemException("internal", e.getMessage())));
    }
  }

  private RowMapper<SelectItem> selectItemAdapter(SelectParams params) {
    return (ResultSet rs, int rowNum) -> {
      var itemBuilder = SelectItem.builder();

      var metadata = rs.getMetaData();
      var columns = metadata.getColumnCount();
      var iterator = params.fields().iterator();

      for (var i = 1; i <= columns; i++) {
        var column = metadata.getColumnName(i);
        var value = rs.getObject(i);

        if (iterator.hasNext()) {
          var field = iterator.next();

          if(field.alias().isPresent()) {
            column = field.alias().get();
          } else if (!"*".equals(field.name())) {
            column = field.name();
          }

        }

        itemBuilder.put(column, value);
      }

      return itemBuilder.build();
    };
  }

  private void isNotReference(Where.Operator operator, JdbcClient.StatementSpec sql, Object value) {
    if (operator != NOT_NULL && operator != NULL) {
      sql.param(value);
    }
  }
}
