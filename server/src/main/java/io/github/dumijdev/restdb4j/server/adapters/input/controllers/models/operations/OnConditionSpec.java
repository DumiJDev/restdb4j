package io.github.dumijdev.restdb4j.server.adapters.input.controllers.models.operations;

import java.util.Optional;

public record OnConditionSpec(OnSideSpec left, String operator, Optional<OnSideSpec> right, Object value) {
}
