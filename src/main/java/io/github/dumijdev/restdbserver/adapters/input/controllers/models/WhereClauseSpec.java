package io.github.dumijdev.restdbserver.adapters.input.controllers.models;

import java.util.Set;

public record WhereClauseSpec(Set<WhereConditionSpec> conditions, String logic) {
}
