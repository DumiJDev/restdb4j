package io.github.dumijdev.restdb4j.server.adapters.input.controllers.models;

import java.util.Map;

public record UpdateRequest(
    String sqlQuery,
    String schema,
    String alias,
    Map<String, Object> data,
    WhereClauseSpec where
) {
}
