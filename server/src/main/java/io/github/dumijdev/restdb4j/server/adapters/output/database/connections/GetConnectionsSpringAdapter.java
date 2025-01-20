package io.github.dumijdev.restdb4j.server.adapters.output.database.connections;

import io.github.dumijdev.restdb4j.server.adapters.output.database.RestD4JRepository;
import io.github.dumijdev.restdb4j.server.application.core.domain.connection.Connection;
import io.github.dumijdev.restdb4j.server.application.core.domain.connection.GetConnectionsParams;
import io.github.dumijdev.restdb4j.server.application.ports.output.connections.GetConnectionsOutputPort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetConnectionsSpringAdapter implements GetConnectionsOutputPort {
  private final RestD4JRepository repository;

  public GetConnectionsSpringAdapter(RestD4JRepository repository) {
    this.repository = repository;
  }

  @Override
  public List<Connection> getAll(GetConnectionsParams params) {
    return repository.findAll()
        .stream()
        .skip(params.offset())
        .limit(params.limit()).toList();
  }
}
