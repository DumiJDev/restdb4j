package io.github.dumijdev.restdbserver.adapters.input.controllers.models;

import java.util.Optional;

public record PaginationSpec(Optional<Integer> limit,
                             Optional<Integer> offset) {
}
