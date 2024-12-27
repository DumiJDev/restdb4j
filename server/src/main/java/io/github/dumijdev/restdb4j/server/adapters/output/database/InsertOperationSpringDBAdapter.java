package io.github.dumijdev.restdb4j.server.adapters.output.database;

import io.github.dumijdev.restdb4j.server.adapters.output.database.utils.SQLGenerator;
import io.github.dumijdev.restdb4j.server.application.core.domain.exceptions.InvalidException;
import io.github.dumijdev.restdb4j.server.application.core.domain.exceptions.ValidationException;
import io.github.dumijdev.restdb4j.server.application.core.domain.insert.InsertParams;
import io.github.dumijdev.restdb4j.server.application.core.domain.insert.InsertResult;
import io.github.dumijdev.restdb4j.server.application.ports.output.InsertOperationOutputPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class InsertOperationSpringDBAdapter implements InsertOperationOutputPort {
  private final JdbcClient client;
  private final SQLGenerator generator;

  public InsertOperationSpringDBAdapter(JdbcClient client, SQLGenerator generator) {
    this.client = client;
    this.generator = generator;
  }

  @Override
  public InsertResult insert(InsertParams params) {
    try {
      var isCustomSqlQuery = params.sqlQuery().isPresent();
      var sqlString = isCustomSqlQuery ? "INSERT INTO %s".formatted(params.sqlQuery()) : generator.generateInsert(params);

      log.info("Insert SQL: {}", sqlString);
      var sql = client.sql(sqlString);

      if (!isCustomSqlQuery) {
        for (var fields : params.data().entrySet()) {
          sql.param(fields.getKey(), fields.getValue());
        }
      }

      sql.update();

      return new InsertResult();

    } catch (Exception e) {
      throw new InvalidException(List.of(new ValidationException.ItemException("internal", e.getMessage())));
    }
  }
}
