package io.github.dumijdev.restdb4j.server.application.core.service.connections;

import io.github.dumijdev.restdb4j.server.application.core.domain.connection.Connection;
import io.github.dumijdev.restdb4j.server.application.core.domain.connection.GetConnectionsParams;
import io.github.dumijdev.restdb4j.server.application.ports.input.connections.GetConnectionsInputPort;
import io.github.dumijdev.restdb4j.server.application.ports.output.connections.GetConnectionsOutputPort;

import java.util.List;

public class GetConnectionsService implements GetConnectionsInputPort {
  private final GetConnectionsOutputPort outputPort;

  public GetConnectionsService(GetConnectionsOutputPort outputPort) {
    this.outputPort = outputPort;
  }

  @Override
  public List<Connection> getAll(GetConnectionsParams params) {
    validate(params);

    return outputPort.getAll(params);
  }

  private void validate(GetConnectionsParams params) {

  }
}
