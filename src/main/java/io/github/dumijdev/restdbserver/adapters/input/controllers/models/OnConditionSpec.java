package io.github.dumijdev.restdbserver.adapters.input.controllers.models;

import java.util.Optional;

public record OnConditionSpec(OnSideSpec left, String operator, Optional<OnSideSpec> right, Object value) {
}
