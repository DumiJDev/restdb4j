package io.github.dumijdev.restdbserver.application.core.domain.select;

import java.util.Arrays;
import java.util.function.Predicate;

public enum JoinType {
  LEFT("left"), RIGHT("right"), INNER("inner"), OUTER("outer"), FULL("full"), UNKNOWN("");

  private final String value;

  public String value() {
    return value;
  }

  JoinType(String value) {
    this.value = value;
  }

  public static JoinType from(String value) {
    Predicate<JoinType> predicate = joinType -> joinType.value.equals(value);
    return Arrays.stream(values()).filter(predicate).findFirst().orElse(UNKNOWN);
  }

}
