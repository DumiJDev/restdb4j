package io.github.dumijdev.restdb4j.server.adapters.output.database;

import io.github.dumijdev.restdb4j.server.adapters.output.database.utils.SQLGenerator;
import io.github.dumijdev.restdb4j.server.application.core.domain.common.Where;
import io.github.dumijdev.restdb4j.server.application.core.domain.delete.DeleteParams;
import io.github.dumijdev.restdb4j.server.application.core.domain.delete.DeleteResult;
import io.github.dumijdev.restdb4j.server.application.core.domain.exceptions.InvalidException;
import io.github.dumijdev.restdb4j.server.application.core.domain.exceptions.ValidationException;
import io.github.dumijdev.restdb4j.server.application.ports.output.DeleteOperationOutputPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Component;

import java.util.List;

import static io.github.dumijdev.restdb4j.server.application.core.domain.common.Where.Operator.NOT_NULL;
import static io.github.dumijdev.restdb4j.server.application.core.domain.common.Where.Operator.NULL;

@Component
@Slf4j(topic = "DeleteOperation")
public class DeleteOperationSpringDBAdapter implements DeleteOperationOutputPort {
  private final JdbcClient client;
  private final SQLGenerator sqlGenerator;

  public DeleteOperationSpringDBAdapter(JdbcClient client, SQLGenerator sqlGenerator) {
    this.client = client;
    this.sqlGenerator = sqlGenerator;
  }

  @Override
  public DeleteResult update(DeleteParams params) {
    try {
      var isCustomSqlQuery = params.sqlQuery().isPresent();
      var sqlString = isCustomSqlQuery ? "SELECT %s".formatted(params.sqlQuery().get()) : sqlGenerator.generateDelete(params);

      log.info("Delete SQL: {}", sqlString);
      var sql = client.sql(sqlString);

      if (!isCustomSqlQuery && params.where().isPresent()) {
        params.where().get().conditions()
            .forEach(condition -> isNotReference(condition.operator(), sql, condition.value()));
      }

      sql.update();
      return new DeleteResult();
    } catch (Exception e) {
      log.error("Delete operation failed", e);
      throw new InvalidException(List.of(new ValidationException.ItemException("internal", e.getMessage())));
    }
  }

  private void isNotReference(Where.Operator operator, JdbcClient.StatementSpec sql, Object value) {
    if (operator != NOT_NULL && operator != NULL) {
      sql.param(value);
    }
  }
}
