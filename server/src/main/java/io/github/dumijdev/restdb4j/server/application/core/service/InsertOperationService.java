package io.github.dumijdev.restdb4j.server.application.core.service;

import io.github.dumijdev.restdb4j.server.application.core.domain.insert.InsertParams;
import io.github.dumijdev.restdb4j.server.application.core.domain.insert.InsertResult;
import io.github.dumijdev.restdb4j.server.application.ports.input.InsertOperationInputPort;
import io.github.dumijdev.restdb4j.server.application.ports.output.InsertOperationOutputPort;

public class InsertOperationService implements InsertOperationInputPort {
  private final InsertOperationOutputPort outputPort;

  public InsertOperationService(InsertOperationOutputPort outputPort) {
    this.outputPort = outputPort;
  }

  @Override
  public InsertResult insert(InsertParams params) {
    validate(params);

    return outputPort.insert(params);
  }

  private void validate(InsertParams params) {

  }
}
