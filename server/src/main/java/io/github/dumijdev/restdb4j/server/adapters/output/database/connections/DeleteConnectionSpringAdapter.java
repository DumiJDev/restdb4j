package io.github.dumijdev.restdb4j.server.adapters.output.database.connections;

import io.github.dumijdev.restdb4j.server.adapters.output.database.RestD4JRepository;
import io.github.dumijdev.restdb4j.server.application.core.domain.connection.DeleteConnectionParams;
import io.github.dumijdev.restdb4j.server.application.ports.output.connections.DeleteConnectionOutputPort;
import org.springframework.stereotype.Component;

@Component
public class DeleteConnectionSpringAdapter implements DeleteConnectionOutputPort {
  private final RestD4JRepository repository;

  public DeleteConnectionSpringAdapter(RestD4JRepository repository) {
    this.repository = repository;
  }


  @Override
  public void delete(DeleteConnectionParams params) {
    repository.deleteById(params.name());
  }
}
