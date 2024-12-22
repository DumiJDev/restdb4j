package io.github.dumijdev.restdbserver.application.core.domain.exceptions;

import java.util.List;

public class ValidationException extends RuntimeException {
  private final List<ItemException> exceptions;

  public ValidationException(List<ItemException> exceptions) {
    this.exceptions = exceptions;
  }

  public List<ItemException> exceptions() {
    return exceptions;
  }

  public record ItemException(String field, String message) {}
}
