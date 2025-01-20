package io.github.dumijdev.restdb4j.server.application.ports.output.connections;

import io.github.dumijdev.restdb4j.server.application.core.domain.connection.Connection;
import io.github.dumijdev.restdb4j.server.application.core.domain.connection.GetConnectionsParams;

import java.util.List;

public interface GetConnectionsOutputPort {
  List<Connection> getAll(GetConnectionsParams params);
}
