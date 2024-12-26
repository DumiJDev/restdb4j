package io.github.dumijdev.restdb4j.server.adapters.input.controllers.models;

import java.util.Optional;

public record FieldSpec(String name, Optional<String> alias) {
}
