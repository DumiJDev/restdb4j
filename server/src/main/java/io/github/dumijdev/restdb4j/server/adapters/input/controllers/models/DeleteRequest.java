package io.github.dumijdev.restdb4j.server.adapters.input.controllers.models;

public record DeleteRequest(
    String sqlQuery,
    String schema,
    String alias,
    WhereClauseSpec where
) {
}
