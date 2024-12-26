package io.github.dumijdev.restdb4j.server.application.ports.output;


import io.github.dumijdev.restdb4j.server.application.core.domain.select.SelectParams;
import io.github.dumijdev.restdb4j.server.application.core.domain.select.SelectResult;

public interface SelectOperationOutputPort {
  SelectResult select(SelectParams params);
}
