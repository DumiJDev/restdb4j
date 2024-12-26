package io.github.dumijdev.restdb4j.server.application.core.domain.update;

import io.github.dumijdev.restdb4j.server.application.core.domain.common.Where;

import java.util.Map;
import java.util.Optional;

public record UpdateParams(String table, Optional<String> alias, Optional<String> schema, Map<String, Object> data, Optional<Where> where) {
}
