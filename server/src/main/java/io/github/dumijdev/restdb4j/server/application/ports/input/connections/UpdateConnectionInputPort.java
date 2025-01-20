package io.github.dumijdev.restdb4j.server.application.ports.input.connections;

import io.github.dumijdev.restdb4j.server.application.core.domain.connection.Connection;
import io.github.dumijdev.restdb4j.server.application.core.domain.connection.UpdateConnectionParams;

public interface UpdateConnectionInputPort {
  Connection update(UpdateConnectionParams params);
}
