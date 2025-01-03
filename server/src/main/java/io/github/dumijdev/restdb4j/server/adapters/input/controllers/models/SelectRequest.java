package io.github.dumijdev.restdb4j.server.adapters.input.controllers.models;

import java.util.Set;

public record SelectRequest(
    String sqlQuery,
    String schema,
    String alias,
    Set<FieldSpec> fields,
    WhereClauseSpec where,
    Set<String> groupBy,
    Set<OrderClauseSpec> order,
    Set<JoinSpec> joins,
    PaginationSpec pagination
) {
}
