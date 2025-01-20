package io.github.dumijdev.restdb4j.server.application.core.service.connections;

import io.github.dumijdev.restdb4j.server.application.core.domain.connection.Connection;
import io.github.dumijdev.restdb4j.server.application.core.domain.connection.UpdateConnectionParams;
import io.github.dumijdev.restdb4j.server.application.ports.input.connections.UpdateConnectionInputPort;
import io.github.dumijdev.restdb4j.server.application.ports.output.connections.UpdateConnectionOutputPort;

public class UpdateConnectionService implements UpdateConnectionInputPort {
  private final UpdateConnectionOutputPort outputPort;

  public UpdateConnectionService(UpdateConnectionOutputPort outputPort) {
    this.outputPort = outputPort;
  }

  @Override
  public Connection update(UpdateConnectionParams params) {
    validate(params);
    return outputPort.update(params);
  }

  private void validate(UpdateConnectionParams params) {

  }
}
