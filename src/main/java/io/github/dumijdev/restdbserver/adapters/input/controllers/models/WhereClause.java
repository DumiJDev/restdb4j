package io.github.dumijdev.restdbserver.adapters.input.controllers.models;

import java.util.Set;

public record WhereClause(Set<WhereCondition> conditions, String logic) {
}
