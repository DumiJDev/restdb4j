package io.github.dumijdev.restdbserver.adapters.input.controllers.models;

import java.util.Optional;

public record FieldSpec(String name, Optional<String> alias) {
}
