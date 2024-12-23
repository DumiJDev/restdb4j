package io.github.dumijdev.restdbserver.adapters.input.controllers.factories;

import io.github.dumijdev.restdbserver.adapters.input.controllers.models.SelectResponse;
import io.github.dumijdev.restdbserver.application.core.domain.select.SelectItem;
import io.github.dumijdev.restdbserver.application.core.domain.select.SelectResult;

public class SelectResponseFactory {
  public static SelectResponse createFromSelectResult(SelectResult result) {
    return new SelectResponse(
        result.items().stream()
            .map(SelectItem::toMap)
            .toList(),
        result.total()
    );
  }
}
