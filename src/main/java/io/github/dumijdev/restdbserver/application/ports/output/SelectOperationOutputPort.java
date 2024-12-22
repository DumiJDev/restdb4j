package io.github.dumijdev.restdbserver.application.ports.output;


import io.github.dumijdev.restdbserver.application.core.domain.select.SelectParams;
import io.github.dumijdev.restdbserver.application.core.domain.select.SelectResult;

public interface SelectOperationOutputPort {
  SelectResult select(SelectParams params);
}
