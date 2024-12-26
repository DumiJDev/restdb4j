package io.github.dumijdev.restdb4j.server.application.ports.input;

import io.github.dumijdev.restdb4j.server.application.core.domain.select.SelectParams;
import io.github.dumijdev.restdb4j.server.application.core.domain.select.SelectResult;

public interface SelectOperationInputPort {
  SelectResult select(SelectParams params);
}
