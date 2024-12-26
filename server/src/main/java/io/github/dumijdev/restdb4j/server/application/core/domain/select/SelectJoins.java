package io.github.dumijdev.restdb4j.server.application.core.domain.select;

import java.util.Optional;
import java.util.Set;

public record SelectJoins(JoinType type, String table, Optional<String> alias, Set<SelectOnCondition> on) {
}
