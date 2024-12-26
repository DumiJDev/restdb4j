package io.github.dumijdev.restdb4j.server.adapters.input.controllers.models;

public record WhereConditionSpec(String field, String table, String operator, Object value) {
}
