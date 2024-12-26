package io.github.dumijdev.restdb4j.server.adapters.output.database.utils;

import io.github.dumijdev.restdb4j.server.application.core.domain.select.SelectParams;

import java.util.Optional;

public class OracleStrategy extends MySQLStrategy implements DatabaseStrategy {

  @Override
  public String generateSelect(SelectParams params) {
    if (params.offset().isPresent() || params.limit().isPresent()) {
      StringBuilder sql = new StringBuilder("SELECT ");
      appendPagination(sql, params);
      return sql.toString();
    }

    return super.generateSelect(params);
  }

  private void appendTable(StringBuilder sql, String table, Optional<String> schema) {
    schema.ifPresentOrElse(
        s -> sql.append(s).append(".").append(table),
        () -> sql.append(table)
    );
  }

  private void appendOrderBy(StringBuilder sql, SelectParams params) {
    if (params.order() != null && !params.order().isEmpty()) {
      sql.append(" ORDER BY ");
      sql.append(params.order().stream()
          .map(order -> order.field() + " " + order.direction())
          .reduce((o1, o2) -> o1 + ", " + o2)
          .orElse(""));
    }
  }

  private void appendPagination(StringBuilder sql, SelectParams params) {
    sql.setLength(0);
    appendFields(sql, params);
    appendTable(sql, params.table(), params.schema());

    params.where().ifPresent(where -> appendWhere(sql, params.where().get()));

    appendOrderBy(sql, params);

    int offset = params.offset().orElse(0);
    int limit = params.limit().orElse(Integer.MAX_VALUE);

    String paginatedQuery = String.format("""
        SELECT * FROM (
            SELECT a.*, ROWNUM rnum FROM (%s) a
            WHERE ROWNUM <= %d
        ) WHERE rnum > %d
        """, sql, offset + limit, offset);

    sql.setLength(0);
    sql.append(paginatedQuery);
  }
}
