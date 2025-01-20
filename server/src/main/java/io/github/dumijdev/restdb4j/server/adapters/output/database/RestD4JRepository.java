package io.github.dumijdev.restdb4j.server.adapters.output.database;

import io.github.dumijdev.restdb4j.server.application.core.domain.connection.Connection;

import java.util.List;
import java.util.Optional;

public interface RestD4JRepository {
  List<Connection> findAll();
  Optional<Connection> findById(String id);
  Connection save(Connection connection);
  void deleteById(String id);
}
