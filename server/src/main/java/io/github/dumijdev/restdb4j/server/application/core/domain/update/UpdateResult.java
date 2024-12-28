package io.github.dumijdev.restdb4j.server.application.core.domain.update;

public record UpdateResult() {
  public static UpdateResult empty() {
    return new UpdateResult();
  }
}
