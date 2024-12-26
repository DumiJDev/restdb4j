package io.github.dumijdev.restdb4j.server.application.core.domain.insert;

import java.util.Map;
import java.util.Optional;

public record InsertParams(String table, Optional<String> alias, Optional<String> schema, Map<String, String> data) {
}
