package io.github.dumijdev.restdb4j.server.adapters.input.controllers.models;

import java.util.Optional;
import java.util.Set;

public record JoinSpec(String type, String table, Optional<String> alias, Set<OnConditionSpec> on) {
}
