package io.github.dumijdev.restdb4j.server.adapters.output.database.connections;

import io.github.dumijdev.restdb4j.server.adapters.output.database.RestD4JRepository;
import io.github.dumijdev.restdb4j.server.application.core.domain.connection.Connection;
import io.github.dumijdev.restdb4j.server.application.core.domain.connection.CreateConnectionParams;
import io.github.dumijdev.restdb4j.server.application.ports.output.connections.CreateConnectionOutputPort;
import org.springframework.stereotype.Component;

@Component
public class CreateConnectionSpringAdapter implements CreateConnectionOutputPort {
  private final RestD4JRepository repository;

  public CreateConnectionSpringAdapter(RestD4JRepository repository) {
    this.repository = repository;
  }

  @Override
  public Connection create(CreateConnectionParams params) {
    return repository.save(new Connection(
        params.name(),
        params.host(),
        params.port(),
        params.database(),
        params.jdbcUrl().orElse(null),
        params.user(),
        params.password(),
        params.jdbcClassDriver().orElse(null),
        params.platform()
    ));
  }
}
