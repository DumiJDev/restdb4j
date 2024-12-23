package io.github.dumijdev.restdbserver.application.core.service;

import io.github.dumijdev.restdbserver.application.core.domain.insert.InsertParams;
import io.github.dumijdev.restdbserver.application.core.domain.insert.InsertResult;
import io.github.dumijdev.restdbserver.application.ports.input.InsertOperationInputPort;
import io.github.dumijdev.restdbserver.application.ports.output.InsertOperationOutputPort;

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
