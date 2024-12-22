package io.github.dumijdev.restdbserver.adapters.output.database;

import io.github.dumijdev.restdbserver.adapters.output.database.utils.SQLGenerator;
import io.github.dumijdev.restdbserver.application.core.domain.exceptions.InvalidException;
import io.github.dumijdev.restdbserver.application.core.domain.exceptions.ValidationException;
import io.github.dumijdev.restdbserver.application.core.domain.select.SelectItem;
import io.github.dumijdev.restdbserver.application.core.domain.select.SelectParams;
import io.github.dumijdev.restdbserver.application.core.domain.select.SelectResult;
import io.github.dumijdev.restdbserver.application.ports.output.SelectOperationOutputPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
      var sqlString = sqlGenerator.generateSelect(params);
      log.info("Select SQL: {}", sqlString);
      var sql = client.sql(sqlString);

      if (params.where().isPresent()) {
        for (var condition : params.where().get().conditions()) {
          sql.param(condition.field(), condition.value());
        }
      }

      var results = sql.query(this::selectItemAdapter).list();

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

  private SelectItem selectItemAdapter(ResultSet rs, int rowNum) throws SQLException {
    var itemBuilder = SelectItem.builder();

    var metadata = rs.getMetaData();
    var columns = metadata.getColumnCount();

    for (var i = 1; i <= columns; i++) {
      var column = metadata.getColumnName(i);
      var value = rs.getObject(i);

      itemBuilder.put(column, value);
    }

    return itemBuilder.build();
  }
}
