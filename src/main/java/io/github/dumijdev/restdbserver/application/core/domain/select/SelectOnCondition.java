package io.github.dumijdev.restdbserver.application.core.domain.select;

import io.github.dumijdev.restdbserver.application.core.domain.common.Where;

import java.util.Optional;

import static java.util.Optional.ofNullable;

public record SelectOnCondition(SelectOnSide left, Optional<SelectOnSide> right, Where.Operator operator, Optional<Object> value) {

  public static SelectOnConditionBuilder builder() {
    return new SelectOnConditionBuilder();
  }

  public static final class SelectOnConditionBuilder {
    private SelectOnSide left;
    private SelectOnSide right;
    private Where.Operator operator;
    private Object value;

    private SelectOnConditionBuilder() {
    }

    public SelectOnConditionBuilder left(SelectOnSide left) {
      this.left = left;
      return this;
    }

    public SelectOnConditionBuilder right(SelectOnSide right) {
      this.right = right;
      return this;
    }

    public SelectOnConditionBuilder operator(Where.Operator operator) {
      this.operator = operator;
      return this;
    }

    public SelectOnConditionBuilder value(Object value) {
      this.value = value;
      return this;
    }

    public SelectOnCondition build() {
      return new SelectOnCondition(left, ofNullable(right), operator, ofNullable(value));
    }
  }
}
