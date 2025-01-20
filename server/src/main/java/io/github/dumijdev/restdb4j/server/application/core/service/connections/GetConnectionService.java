package io.github.dumijdev.restdb4j.server.application.core.service.connections;

import io.github.dumijdev.restdb4j.server.application.core.domain.connection.Connection;
import io.github.dumijdev.restdb4j.server.application.core.domain.connection.GetConnectionParams;
import io.github.dumijdev.restdb4j.server.application.ports.input.connections.GetConnectionInputPort;
import io.github.dumijdev.restdb4j.server.application.ports.output.connections.GetConnectionOutputPort;

import java.util.Optional;

public class GetConnectionService implements GetConnectionInputPort {
  private final GetConnectionOutputPort outputPort;

  public GetConnectionService(GetConnectionOutputPort outputPort) {
    this.outputPort = outputPort;
  }

  @Override
  public Optional<Connection> get(GetConnectionParams params) {
    validate(params);

    return outputPort.get(params);
  }

  private void validate(GetConnectionParams params) {

  }
}
