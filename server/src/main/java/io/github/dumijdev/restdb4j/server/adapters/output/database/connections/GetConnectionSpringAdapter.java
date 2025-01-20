package io.github.dumijdev.restdb4j.server.adapters.output.database.connections;

import io.github.dumijdev.restdb4j.server.adapters.output.database.RestD4JRepository;
import io.github.dumijdev.restdb4j.server.application.core.domain.connection.Connection;
import io.github.dumijdev.restdb4j.server.application.core.domain.connection.GetConnectionParams;
import io.github.dumijdev.restdb4j.server.application.ports.output.connections.GetConnectionOutputPort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class GetConnectionSpringAdapter implements GetConnectionOutputPort {
  private final RestD4JRepository repository;

  public GetConnectionSpringAdapter(RestD4JRepository repository) {
    this.repository = repository;
  }

  @Override
  public Optional<Connection> get(GetConnectionParams params) {
    return repository.findById(params.name());
  }
}
