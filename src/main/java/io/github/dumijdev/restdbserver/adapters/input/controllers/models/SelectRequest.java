package io.github.dumijdev.restdbserver.adapters.input.controllers.models;

import java.util.Optional;
import java.util.Set;

public record SelectRequest(
    String schema,
    Set<String> fields,
    WhereClause where,
    Set<String> groupBy,
    Set<OrderClause> order,
    Optional<Integer> limit,
    Optional<Integer> offset
) {
}
