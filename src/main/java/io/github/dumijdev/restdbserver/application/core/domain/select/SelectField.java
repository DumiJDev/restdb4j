package io.github.dumijdev.restdbserver.application.core.domain.select;

import java.util.Optional;

public record SelectField(String name, Optional<String> table, Optional<String> alias) {
}
