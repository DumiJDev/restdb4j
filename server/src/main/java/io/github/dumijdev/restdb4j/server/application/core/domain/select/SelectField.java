package io.github.dumijdev.restdb4j.server.application.core.domain.select;

import java.util.Optional;

public record SelectField(String name, Optional<String> table, Optional<String> alias) {
}
