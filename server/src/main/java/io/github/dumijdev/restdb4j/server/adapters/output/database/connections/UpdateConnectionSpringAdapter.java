package io.github.dumijdev.restdb4j.server.adapters.output.database.connections;

import io.github.dumijdev.restdb4j.server.adapters.output.database.RestD4JRepository;
import io.github.dumijdev.restdb4j.server.application.core.domain.connection.Connection;
import io.github.dumijdev.restdb4j.server.application.core.domain.connection.UpdateConnectionParams;
import io.github.dumijdev.restdb4j.server.application.core.domain.exceptions.InvalidException;
import io.github.dumijdev.restdb4j.server.application.core.domain.exceptions.ValidationException;
import io.github.dumijdev.restdb4j.server.application.ports.output.connections.UpdateConnectionOutputPort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UpdateConnectionSpringAdapter implements UpdateConnectionOutputPort {
  private final RestD4JRepository repository;

  public UpdateConnectionSpringAdapter(RestD4JRepository repository) {
    this.repository = repository;
  }

  @Override
  public Connection update(UpdateConnectionParams params) {
    var connection = repository.findById(params.name());
    if (connection.isEmpty()) {
      throw new InvalidException(List.of(new ValidationException.ItemException("internal", "Connection not found")));
    }

    return repository.save(connection.get());
  }
}
