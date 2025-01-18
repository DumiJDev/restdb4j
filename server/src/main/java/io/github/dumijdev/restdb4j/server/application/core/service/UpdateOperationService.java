package io.github.dumijdev.restdb4j.server.application.core.service;

import io.github.dumijdev.restdb4j.server.application.core.domain.update.UpdateParams;
import io.github.dumijdev.restdb4j.server.application.core.domain.update.UpdateResult;
import io.github.dumijdev.restdb4j.server.application.ports.input.UpdateOperationInputPort;
import io.github.dumijdev.restdb4j.server.application.ports.output.UpdateOperationOutputPort;

public class UpdateOperationService implements UpdateOperationInputPort {
  private final UpdateOperationOutputPort outputPort;

  public UpdateOperationService(UpdateOperationOutputPort outputPort) {
    this.outputPort = outputPort;
  }

  @Override
  public UpdateResult update(UpdateParams params) {
    validate(params);
    return outputPort.update(params);
  }

  private void validate(UpdateParams params) {

  }
}
