package io.github.dumijdev.restdb4j.server.application.ports.output.connections;

import io.github.dumijdev.restdb4j.server.application.core.domain.connection.DeleteConnectionParams;

public interface DeleteConnectionOutputPort {
  void delete(DeleteConnectionParams params);
}
