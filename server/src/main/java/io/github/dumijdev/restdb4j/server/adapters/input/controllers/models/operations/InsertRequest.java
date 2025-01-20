package io.github.dumijdev.restdb4j.server.adapters.input.controllers.models.operations;

import java.util.Map;

public record InsertRequest(String sqlQuery, String schema, Map<String, Object> data) {
}
