package io.github.dumijdev.restdb4j.server.adapters.input.controllers.models.operations;

public record DeleteRequest(
    String sqlQuery,
    String schema,
    String alias,
    WhereClauseSpec where
) {
}
