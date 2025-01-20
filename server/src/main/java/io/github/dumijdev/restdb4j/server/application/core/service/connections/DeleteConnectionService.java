package io.github.dumijdev.restdb4j.server.application.core.service.connections;

import io.github.dumijdev.restdb4j.server.application.core.domain.connection.DeleteConnectionParams;
import io.github.dumijdev.restdb4j.server.application.ports.input.connections.DeleteConnectionInputPort;
import io.github.dumijdev.restdb4j.server.application.ports.output.connections.DeleteConnectionOutputPort;

public class DeleteConnectionService implements DeleteConnectionInputPort {
  private final DeleteConnectionOutputPort outputPort;

  public DeleteConnectionService(DeleteConnectionOutputPort outputPort) {
    this.outputPort = outputPort;
  }

  @Override
  public void delete(DeleteConnectionParams params) {
    validate(params);

    outputPort.delete(params);
  }

  private void validate(DeleteConnectionParams params) {

  }
}
