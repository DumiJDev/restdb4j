package io.github.dumijdev.restdbserver.application.core.domain.select;

import java.util.*;

import static java.util.stream.Collectors.toSet;

public final class SelectItem implements Iterable<SelectItem.Field> {
  private final Map<String, Object> data = new HashMap<>();

  private SelectItem(Map<String, Object> data) {
    this.data.putAll(data);
  }

  private SelectItem() {
  }

  public static SelectItemBuilder builder() {
    return new SelectItemBuilder();
  }

  public Optional<Object> get(String key) {
    return Optional.ofNullable(data.get(key));
  }

  public Set<String> keys() {
    return data.keySet();
  }

  public Set<Field> fields() {
    return data.keySet()
        .stream()
        .map(this::mapToFieldType)
        .collect(toSet());
  }

  public Map<String, Object> toMap() {
    return data;
  }

  private Field mapToFieldType(String field) {
    return new Field(field, data.get(field));
  }

  @Override
  public String toString() {
    return "SelectItem{" +
        "data=" + data +
        '}';
  }

  @Override
  public Iterator<Field> iterator() {
    return fields().iterator();
  }

  public record Field(String name, Object value) {
  }

  public static class SelectItemBuilder {
    private final Map<String, Object> data = new HashMap<>();

    private SelectItemBuilder() {}

    public SelectItemBuilder put(String key, Object value) {
      data.put(key, value);
      return this;
    }

    public SelectItem build() {
      return new SelectItem(data);
    }
  }
}
