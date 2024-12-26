package io.github.dumijdev.restdb4j.server.adapters.input.controllers.models;

import java.util.Optional;

public record PaginationSpec(Optional<Integer> limit,
                             Optional<Integer> offset) {
}
