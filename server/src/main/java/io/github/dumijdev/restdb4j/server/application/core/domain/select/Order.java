package io.github.dumijdev.restdb4j.server.application.core.domain.select;

import java.util.Arrays;

import static io.github.dumijdev.restdb4j.server.application.core.domain.select.Order.Direction.DESC;

public record Order(String field, Direction direction) {
  public Order(String field) {
    this(field, DESC);
  }
  public enum Direction {
    ASC, DESC;

    public static Direction from(String direction) {
      return Arrays.stream(values())
          .filter(d -> d.toString().equalsIgnoreCase(direction))
          .findFirst()
          .orElse(DESC);
    }
  }
}
