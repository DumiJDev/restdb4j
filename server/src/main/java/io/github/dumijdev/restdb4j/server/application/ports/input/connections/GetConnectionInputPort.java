package io.github.dumijdev.restdb4j.server.application.ports.input.connections;

import io.github.dumijdev.restdb4j.server.application.core.domain.connection.Connection;
import io.github.dumijdev.restdb4j.server.application.core.domain.connection.GetConnectionParams;

import java.util.Optional;

public interface GetConnectionInputPort {
  Optional<Connection> get(GetConnectionParams params);
}
