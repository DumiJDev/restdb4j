package io.github.dumijdev.restdb4j.server.application.core.domain.delete;

import io.github.dumijdev.restdb4j.server.application.core.domain.common.Where;

import java.util.Optional;

public record DeleteParams(String table, Optional<String> alias, Optional<String> schema, Optional<Where> where,
                           Optional<String> sqlQuery) {
}
