package io.github.dumijdev.restdbserver.application.core.domain.delete;

import io.github.dumijdev.restdbserver.application.core.domain.common.Where;

import java.util.Optional;

public record DeleteParams(String table, Optional<String> schema, Optional<Where> where) {
}
