package io.github.dumijdev.restdbserver.application.core.domain.update;

import io.github.dumijdev.restdbserver.application.core.domain.common.Where;

import java.util.Map;
import java.util.Optional;

public record UpdateParams(String table, Optional<String> alias, Optional<String> schema, Map<String, Object> data, Optional<Where> where) {
}
