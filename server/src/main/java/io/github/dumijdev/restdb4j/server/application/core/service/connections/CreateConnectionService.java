package io.github.dumijdev.restdb4j.server.application.core.service.connections;

import io.github.dumijdev.restdb4j.server.application.core.domain.connection.Connection;
import io.github.dumijdev.restdb4j.server.application.core.domain.connection.CreateConnectionParams;
import io.github.dumijdev.restdb4j.server.application.core.domain.exceptions.ValidationException;
import io.github.dumijdev.restdb4j.server.application.ports.input.connections.CreateConnectionInputPort;
import io.github.dumijdev.restdb4j.server.application.ports.output.connections.CreateConnectionOutputPort;

import java.util.LinkedList;

public class CreateConnectionService implements CreateConnectionInputPort {
  private final CreateConnectionOutputPort outputPort;

  public CreateConnectionService(CreateConnectionOutputPort outputPort) {
    this.outputPort = outputPort;
  }

  @Override
  public Connection create(CreateConnectionParams params) {
    validate(params);

    return outputPort.create(params);
  }

  private void validate(CreateConnectionParams params) {
    var errors = new LinkedList<ValidationException.ItemException>();
    if (params == null) {
      errors.add(new ValidationException.ItemException("params", "Cannot be null"));

      throw new ValidationException(errors);
    }

    if (params.host() == null || params.host().isEmpty()) {
      errors.add(new ValidationException.ItemException("host", "Cannot be null or empty"));
    }

    if (params.port() == null || params.port().isEmpty()) {
      errors.add(new ValidationException.ItemException("port", "Cannot be null or empty"));
    }

    if (params.user() == null || params.user().isEmpty()) {
      errors.add(new ValidationException.ItemException("user", "Cannot be null or empty"));
    }

    if (params.database() == null || params.database().isEmpty()) {
      errors.add(new ValidationException.ItemException("database", "Cannot be null or empty"));
    }

    if (params.jdbcClassDriver().isPresent() && params.jdbcClassDriver().get().isEmpty()) {
      errors.add(new ValidationException.ItemException("jdbcClassDriver", "Cannot be empty"));
    }

    if (params.jdbcUrl().isPresent() && params.jdbcUrl().get().isEmpty()) {
      errors.add(new ValidationException.ItemException("jdbcUrl", "Cannot be empty"));
    }

    if (!errors.isEmpty()) {
      throw new ValidationException(errors);
    }
  }
}
