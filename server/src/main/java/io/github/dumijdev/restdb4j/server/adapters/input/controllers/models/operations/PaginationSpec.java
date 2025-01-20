package io.github.dumijdev.restdb4j.server.adapters.input.controllers.models.operations;

import java.util.Optional;

public record PaginationSpec(Optional<Integer> limit,
                             Optional<Integer> offset) {
}
