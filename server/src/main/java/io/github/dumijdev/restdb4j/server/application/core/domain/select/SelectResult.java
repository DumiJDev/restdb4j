package io.github.dumijdev.restdb4j.server.application.core.domain.select;

import java.util.List;

import static java.util.Collections.unmodifiableList;

public record SelectResult(long total, List<SelectItem> items) {
  public SelectResult(List<SelectItem> items) {
    this(items.size(), unmodifiableList(items));
  }

  private SelectResult() {
    this(List.of());
  }

  public static SelectResult empty() {
    return new SelectResult();
  }
}
