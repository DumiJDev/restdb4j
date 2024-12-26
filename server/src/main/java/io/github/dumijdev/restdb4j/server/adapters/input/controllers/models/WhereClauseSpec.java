package io.github.dumijdev.restdb4j.server.adapters.input.controllers.models;

import java.util.Set;

public record WhereClauseSpec(Set<WhereConditionSpec> conditions, String logic) {
}
