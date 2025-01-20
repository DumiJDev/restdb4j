package io.github.dumijdev.restdb4j.server.application.ports.input.connections;

import io.github.dumijdev.restdb4j.server.application.core.domain.connection.Connection;
import io.github.dumijdev.restdb4j.server.application.core.domain.connection.GetConnectionsParams;

import java.util.List;

public interface GetConnectionsInputPort {
  List<Connection> getAll(GetConnectionsParams params);
}
