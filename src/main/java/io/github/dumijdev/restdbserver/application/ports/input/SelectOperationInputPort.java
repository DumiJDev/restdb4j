package io.github.dumijdev.restdbserver.application.ports.input;

import io.github.dumijdev.restdbserver.application.core.domain.select.SelectParams;
import io.github.dumijdev.restdbserver.application.core.domain.select.SelectResult;

public interface SelectOperationInputPort {
  SelectResult select(SelectParams params);
}
