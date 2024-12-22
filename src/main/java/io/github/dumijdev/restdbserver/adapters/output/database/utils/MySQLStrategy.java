package io.github.dumijdev.restdbserver.adapters.output.database.utils;

import io.github.dumijdev.restdbserver.application.core.domain.common.Where;
import io.github.dumijdev.restdbserver.application.core.domain.delete.DeleteParams;
import io.github.dumijdev.restdbserver.application.core.domain.insert.InsertParams;
import io.github.dumijdev.restdbserver.application.core.domain.select.SelectParams;
import io.github.dumijdev.restdbserver.application.core.domain.update.UpdateParams;

import java.util.Optional;

public class MySQLStrategy implements DatabaseStrategy {
  @Override
  public String generateSelect(SelectParams params) {
    StringBuilder sql = new StringBuilder("SELECT ");
    appendFields(sql, params);
    appendTable(sql, params);
    params.where().ifPresent(where -> appendWhere(sql, where));
    appendOrderBy(sql, params);
    appendPagination(sql, params);
    return sql.toString();
  }

  @Override
  public String generateInsert(InsertParams params) {
    StringBuilder sql = new StringBuilder("INSERT INTO ");
    appendTable(sql, params.table(), params.schema());

    sql.append(" (");
    sql.append(String.join(", ", params.data().keySet()));
    sql.append(") VALUES (");
    sql.append(String.join(", ", params.data().values().stream().map(v -> "?").toList()));
    sql.append(")");
    return sql.toString();
  }

  @Override
  public String generateUpdate(UpdateParams params) {
    StringBuilder sql = new StringBuilder("UPDATE ");
    appendTable(sql, params.table(), params.schema());

    sql.append(" SET ");

    var values = params.data()
        .keySet()
        .stream()
        .map("%s = ?"::formatted)
        .reduce((o1, o2) -> o1 + ", " + o2)
        .orElse("");

    sql.append(values);

    params.where().ifPresent(where -> appendWhere(sql, where));
    return sql.toString();
  }

  @Override
  public String generateDelete(DeleteParams params) {
    StringBuilder sql = new StringBuilder("DELETE FROM ");
    appendTable(sql, params.table(), params.schema());
    params.where().ifPresent(where -> appendWhere(sql, where));
    return sql.toString();
  }

  private void appendFields(StringBuilder sql, SelectParams params) {
    String fields = params.fields().isEmpty() ? "*" : String.join(", ", params.fields());
    sql.append(fields);
  }

  private void appendTable(StringBuilder sql, SelectParams params) {
    sql.append(" FROM ");
    appendTable(sql, params.table(), params.schema());
  }

  protected void appendWhere(StringBuilder sql, Where where) {
    sql.append(" WHERE ");
    sql.append(where.conditions().stream()
        .map(MySQLStrategy::mapClause)
        .reduce((c1, c2) -> c1 + " " + where.symbol() + " " + c2)
        .orElse(""));
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

  private void appendTable(StringBuilder sql, String table, Optional<String> schema) {
    schema.ifPresentOrElse(
        s -> sql.append(s).append(".").append(table),
        () -> sql.append(table)
    );
  }

  private void appendPagination(StringBuilder sql, SelectParams params) {
    params.limit().ifPresent(limit -> sql.append(" LIMIT ").append(limit));
    params.offset().ifPresent(offset -> sql.append(" OFFSET ").append(offset));
  }

  private static String mapClause(Where.Condition condition) {
    return switch (condition.operator()) {
      case EQUALS, GREATER_EQUALS, GREATER, LESSER_EQUALS, LESSER, NOT_EQUALS -> "%s %s :%s".formatted(condition.field(), condition.operator().value(), condition.field());
      case NULL -> "%s IS NULL".formatted(condition.field());
      case NOT_NULL -> "%s IS NOT NULL".formatted(condition.field());
      case LIKE -> "%s LIKE ?".formatted(condition.field());
      default -> "";
    };
  }
}
