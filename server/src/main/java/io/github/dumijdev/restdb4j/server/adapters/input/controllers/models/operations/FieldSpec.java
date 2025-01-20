package io.github.dumijdev.restdb4j.server.adapters.input.controllers.models.operations;

import java.util.Optional;

public record FieldSpec(String name, Optional<String> alias) {
}
