package io.github.dumijdev.restdb4j.server.application.core.domain.exceptions;

import java.util.List;

public class InvalidException extends ValidationException {

  public InvalidException(List<ItemException> exceptions) {
    super(exceptions);
  }
}
