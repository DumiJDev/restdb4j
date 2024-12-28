package io.github.dumijdev.restdb4j.server.application.core.service;

import io.github.dumijdev.restdb4j.server.application.core.domain.delete.DeleteParams;
import io.github.dumijdev.restdb4j.server.application.core.domain.delete.DeleteResult;
import io.github.dumijdev.restdb4j.server.application.ports.input.DeleteOperationInputPort;
import io.github.dumijdev.restdb4j.server.application.ports.output.DeleteOperationOutputPort;

public class DeleteOperationService implements DeleteOperationInputPort {
  private final DeleteOperationOutputPort outputPort;

  public DeleteOperationService(DeleteOperationOutputPort outputPort) {
    this.outputPort = outputPort;
  }

  @Override
  public DeleteResult delete(DeleteParams params) {
    validate(params);
    return outputPort.update(params);
  }

  private void validate(DeleteParams params) {

  }
}
