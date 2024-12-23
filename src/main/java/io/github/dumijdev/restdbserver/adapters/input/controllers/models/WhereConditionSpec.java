package io.github.dumijdev.restdbserver.adapters.input.controllers.models;

public record WhereConditionSpec(String field, String table, String operator, Object value) {
}
