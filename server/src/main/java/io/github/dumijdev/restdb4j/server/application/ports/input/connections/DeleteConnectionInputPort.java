package io.github.dumijdev.restdb4j.server.application.ports.input.connections;

import io.github.dumijdev.restdb4j.server.application.core.domain.connection.DeleteConnectionParams;

public interface DeleteConnectionInputPort {
  void delete(DeleteConnectionParams params);
}
