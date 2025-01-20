package io.github.dumijdev.restdb4j.server.application.ports.input.connections;

import io.github.dumijdev.restdb4j.server.application.core.domain.connection.Connection;
import io.github.dumijdev.restdb4j.server.application.core.domain.connection.CreateConnectionParams;

public interface CreateConnectionInputPort {
  Connection create(CreateConnectionParams params);
}
