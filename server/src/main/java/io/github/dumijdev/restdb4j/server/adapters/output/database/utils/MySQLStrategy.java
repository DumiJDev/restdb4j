package io.github.dumijdev.restdb4j.server.adapters.output.database.utils;

import io.github.dumijdev.restdb4j.server.application.core.domain.common.Where;
import io.github.dumijdev.restdb4j.server.application.core.domain.delete.DeleteParams;
import io.github.dumijdev.restdb4j.server.application.core.domain.insert.InsertParams;
import io.github.dumijdev.restdb4j.server.application.core.domain.select.SelectField;
import io.github.dumijdev.restdb4j.server.application.core.domain.select.SelectOnCondition;
import io.github.dumijdev.restdb4j.server.application.core.domain.select.SelectParams;
import io.github.dumijdev.restdb4j.server.application.core.domain.update.UpdateParams;

import java.util.Optional;

import static java.util.stream.Collectors.joining;

public class MySQLStrategy implements DatabaseStrategy {

  @Override
  public String generateSelect(SelectParams params) {
    StringBuilder sql = new StringBuilder();
    appendFields(sql, params);
    appendTable(sql, params);
    appendJoins(sql, params);
    params.where().ifPresent(where -> appendWhere(sql, where));
    appendOrderBy(sql, params);
    appendPagination(sql, params);
    return sql.toString();
  }

  private void appendJoins(StringBuilder sql, SelectParams params) {
    if (params.joins() != null && !params.joins().isEmpty()) {
      for (var join : params.joins()) {
        sql.append(" ")
            .append(join.type().name()) // INNER, LEFT, RIGHT, etc.
            .append(" JOIN ")
            .append(join.table());

        if (join.alias().isPresent()) {
          sql.append(' ').append(join.alias().get());
        }

        // Constrói a cláusula ON para cada JOIN
        sql.append(" ON ");
        sql.append(join.on().stream()
            .map(this::mapJoinCondition)
            .reduce((c1, c2) -> c1 + " AND " + c2)
            .orElseThrow(() -> new IllegalArgumentException("JOIN requires at least one ON condition")));
      }
    }
  }

  private String mapJoinCondition(SelectOnCondition condition) {
    if (condition.right().isPresent()) {
      // Comparação entre campos de tabelas
      return "%s.%s %s %s.%s".formatted(
          condition.left().table(), condition.left().field(),
          condition.operator().value(),
          condition.right().get().table(), condition.right().get().field()
      );
    } else if (condition.value().isPresent()) {
      // Comparação com valor estático
      return "%s.%s %s ?".formatted(
          condition.left().table(), condition.left().field(),
          condition.operator().value()
      );
    }
    throw new IllegalArgumentException("JOIN ON condition must specify either a right operand or a value");
  }


  @Override
  public String generateInsert(InsertParams params) {
    StringBuilder sql = new StringBuilder("INSERT INTO ");
    appendTable(sql, params.table(), params.schema(), params.alias());

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
    appendTable(sql, params.table(), params.schema(), params.alias());

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
    appendTable(sql, params.table(), params.schema(), params.alias());
    params.where().ifPresent(where -> appendWhere(sql, where));
    return sql.toString();
  }

  protected void appendFields(StringBuilder sql, SelectParams params) {
    var fields = params.fields().isEmpty() ? "*" : params.fields()
        .stream()
        .map(this::mapToFieldQuery)
        .collect(joining(", "));


    sql.append("SELECT ").append(fields);
  }

  private void appendTable(StringBuilder sql, SelectParams params) {
    sql.append(" FROM ");
    appendTable(sql, params.table(), params.schema(), params.alias());
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

  private void appendTable(StringBuilder sql, String table, Optional<String> schema, Optional<String> tableAlias) {
    schema.ifPresentOrElse(
        s -> sql.append(s).append(".").append(table),
        () -> sql.append(table)
    );

    tableAlias.ifPresent(s -> sql.append(' ').append(s));
  }

  private void appendPagination(StringBuilder sql, SelectParams params) {
    params.limit().ifPresent(limit -> sql.append(" LIMIT ").append(limit));
    params.offset().ifPresent(offset -> sql.append(" OFFSET ").append(offset));
  }

  private static String mapClause(Where.Condition condition) {
    return (condition.table().isPresent() ? condition.table().get() + "." : "") + switch (condition.operator()) {
      case EQUALS, GREATER_EQUALS, GREATER, LESSER_EQUALS, LESSER, NOT_EQUALS ->
          "%s %s ?".formatted(condition.field(), condition.operator().value());
      case NULL -> "%s IS NULL".formatted(condition.field());
      case NOT_NULL -> "%s IS NOT NULL".formatted(condition.field());
      case LIKE -> "%s LIKE ?".formatted(condition.field());
      default -> "";
    };
  }

  private String mapToFieldQuery(SelectField selectField) {
    var field = selectField.name();

    if (selectField.alias().isPresent()) {
      field = "%s AS %s".formatted(field, selectField.alias().get());
    }

    if (selectField.table().isPresent()) {
      field = "%s.%s".formatted(selectField.table().get(), field);
    }

    return field;
  }
}
