package io.github.dumijdev.restdb4j.server.adapters.output.database;

import io.github.dumijdev.restdb4j.server.adapters.output.database.utils.SQLGenerator;
import io.github.dumijdev.restdb4j.server.application.core.domain.common.Where;
import io.github.dumijdev.restdb4j.server.application.core.domain.exceptions.InvalidException;
import io.github.dumijdev.restdb4j.server.application.core.domain.exceptions.ValidationException;
import io.github.dumijdev.restdb4j.server.application.core.domain.update.UpdateParams;
import io.github.dumijdev.restdb4j.server.application.core.domain.update.UpdateResult;
import io.github.dumijdev.restdb4j.server.application.ports.output.UpdateOperationOutputPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Component;

import java.util.List;

import static io.github.dumijdev.restdb4j.server.application.core.domain.common.Where.Operator.NOT_NULL;
import static io.github.dumijdev.restdb4j.server.application.core.domain.common.Where.Operator.NULL;

@Component
@Slf4j(topic = "UpdateOperation")
public class UpdateOperationSpringDBAdapter implements UpdateOperationOutputPort {
  private final JdbcClient client;
  private final SQLGenerator sqlGenerator;

  public UpdateOperationSpringDBAdapter(JdbcClient client, SQLGenerator sqlGenerator) {
    this.client = client;
    this.sqlGenerator = sqlGenerator;
  }

  @Override
  public UpdateResult update(UpdateParams params) {
    try {
      var isCustomSqlQuery = params.sqlQuery().isPresent();
      var sqlString = isCustomSqlQuery ? "UPDATE %s".formatted(params.sqlQuery().get()) : sqlGenerator.generateUpdate(params);

      log.info("Update SQL: {}", sqlString);
      var sql = client.sql(sqlString);

      params.data().values().forEach(sql::param);

      if (!isCustomSqlQuery && params.where().isPresent()) {
        params.where().get().conditions()
            .forEach(condition -> isNotReference(condition.operator(), sql, condition.value()));
      }

      var results = sql.update();

      return new UpdateResult();
    } catch (Exception e) {
      log.error("Occurred an exception", e);
      throw new InvalidException(List.of(new ValidationException.ItemException("internal", e.getMessage())));
    }
  }

  private void isNotReference(Where.Operator operator, JdbcClient.StatementSpec sql, Object value) {
    if (operator != NOT_NULL && operator != NULL) {
      sql.param(value);
    }
  }
}
