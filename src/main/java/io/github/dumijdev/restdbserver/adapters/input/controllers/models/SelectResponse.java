package io.github.dumijdev.restdbserver.adapters.input.controllers.models;

import java.util.List;
import java.util.Map;

public record SelectResponse(List<Map<String, Object>> data, Long total) {
}
