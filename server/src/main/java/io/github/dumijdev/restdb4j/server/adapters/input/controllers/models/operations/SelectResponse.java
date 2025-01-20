package io.github.dumijdev.restdb4j.server.adapters.input.controllers.models.operations;

import java.util.List;
import java.util.Map;

public record SelectResponse(List<Map<String, Object>> data, Long total) {
}
