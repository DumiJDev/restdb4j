package io.github.dumijdev.restdbserver.application.core.domain.insert;

import java.util.Map;
import java.util.Optional;

public record InsertParams(String table, Optional<String> schema, Map<String, String> data) {
}
