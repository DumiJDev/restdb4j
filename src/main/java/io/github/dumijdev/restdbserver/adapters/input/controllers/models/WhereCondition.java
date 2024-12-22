package io.github.dumijdev.restdbserver.adapters.input.controllers.models;

public record WhereCondition(String field, String operator, Object value) {
}
