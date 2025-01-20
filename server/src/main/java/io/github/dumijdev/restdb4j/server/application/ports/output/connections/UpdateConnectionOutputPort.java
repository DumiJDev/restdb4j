package io.github.dumijdev.restdb4j.server.application.ports.output.connections;

import io.github.dumijdev.restdb4j.server.application.core.domain.connection.Connection;
import io.github.dumijdev.restdb4j.server.application.core.domain.connection.UpdateConnectionParams;

public interface UpdateConnectionOutputPort {
  Connection update(UpdateConnectionParams params);
}
