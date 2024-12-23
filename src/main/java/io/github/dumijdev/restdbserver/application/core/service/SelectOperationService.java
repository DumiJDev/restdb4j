package io.github.dumijdev.restdbserver.application.core.service;

import io.github.dumijdev.restdbserver.application.core.domain.common.Where;
import io.github.dumijdev.restdbserver.application.core.domain.exceptions.ValidationException;
import io.github.dumijdev.restdbserver.application.core.domain.exceptions.ValidationException.ItemException;
import io.github.dumijdev.restdbserver.application.core.domain.select.SelectParams;
import io.github.dumijdev.restdbserver.application.core.domain.select.SelectResult;
import io.github.dumijdev.restdbserver.application.ports.input.SelectOperationInputPort;
import io.github.dumijdev.restdbserver.application.ports.output.SelectOperationOutputPort;

import java.util.LinkedList;

import static io.github.dumijdev.restdbserver.application.core.domain.common.Where.Operator.*;

public class SelectOperationService implements SelectOperationInputPort {
  private final SelectOperationOutputPort outputPort;

  public SelectOperationService(SelectOperationOutputPort outputPort) {
    this.outputPort = outputPort;
  }

  @Override
  public SelectResult select(SelectParams params) {
    validate(params);

    return outputPort.select(params);
  }

  private void validate(SelectParams params) {
    var errors = new LinkedList<ItemException>();
    if (params == null) {
      errors.add(new ItemException("params", "Cannot be null"));

      throw new ValidationException(errors);
    }

    if (params.table() == null) {
      errors.add(new ItemException("table", "Cannot be null"));
    }

    if (params.fields() != null && !params.fields().isEmpty()) {
      var i = 0;
      for (var field : params.fields()) {
        if (field.name() == null) {
          errors.add(new ItemException("field[%d]".formatted(i), "Cannot be null"));
        } else if (field.name().isEmpty()) {
          errors.add(new ItemException("field[%d]".formatted(i), "Cannot be empty"));
        }

        if (field.alias().isPresent() && field.alias().get().isEmpty()) {
          errors.add(new ItemException("alias[%d]".formatted(i), "Cannot be empty"));
        }

        i++;
      }
    }

    if (params.where().isPresent()) {
      var where = params.where().get();

      if (where.symbol() != null && !Where.Symbol.validate(where.symbol())) {
        errors.add(new ItemException("symbol", "Invalid value"));
      }
      var i = 0;
      for (var condition : where.conditions()) {
        if (condition.field() == null) {
          errors.add(new ItemException("condition[%d]".formatted(i), "Field Cannot be null"));
        }

        if (condition.operator() == null) {
          errors.add(new ItemException("condition[%d]".formatted(i), "Operator Cannot be null"));
        }

        var operator = condition.operator();
        var isUnary = operator != NOT && operator != NOT_NULL && operator != NULL;

        if (condition.value() == null && isUnary) {
          errors.add(new ItemException("condition[%d]".formatted(i), "Value Cannot be null"));
        }

        i++;
      }
    }

    if (params.joins() != null && !params.joins().isEmpty()) {
      var i = 0;
      for (var join : params.joins()) {
        if (join.table() == null) {
          errors.add(new ItemException("join table[%d]".formatted(i), "Cannot be null"));
        } else if (join.table().isEmpty()) {
          errors.add(new ItemException("join table[%d]".formatted(i), "Cannot be empty"));
        }

        if (join.alias().isPresent() && join.alias().get().isEmpty()) {
          errors.add(new ItemException("join table alias[%d]".formatted(i), "Cannot be empty"));
        }

        for (var onItem : join.on()) {
          if (onItem.left() == null) {
            errors.add(new ItemException("join table left[%d]".formatted(i), "Cannot be null"));
          }

          if (onItem.left() != null) {
            var left = onItem.left();
            if (left.field() == null) {
              errors.add(new ItemException("join table left field[%d]".formatted(i), "Cannot be null"));
            } else if (left.field().isEmpty()) {
              errors.add(new ItemException("join table left field[%d]".formatted(i), "Cannot be empty"));
            }

            if (left.table() == null) {
              errors.add(new ItemException("join table left table[%d]".formatted(i), "Cannot be empty"));
            } else if (left.table().isEmpty()) {
              errors.add(new ItemException("join table left table[%d]".formatted(i), "Cannot be empty"));
            }
          }

          if (onItem.right().isPresent()) {
            var right = onItem.right().get();
            if (right.field() == null) {
              errors.add(new ItemException("join table right field[%d]".formatted(i), "Cannot be null"));
            } else if (right.field().isEmpty()) {
              errors.add(new ItemException("join table right field[%d]".formatted(i), "Cannot be empty"));
            }

            if (right.table() == null) {
              errors.add(new ItemException("join table right table[%d]".formatted(i), "Cannot be null"));
            } else if (right.table().isEmpty()) {
              errors.add(new ItemException("join table right table[%d]".formatted(i), "Cannot be empty"));
            }

          } else if(onItem.value().isEmpty()) {
            errors.add(new ItemException("join table value[%d]".formatted(i), "Cannot be null"));
          }
        }

        i++;
      }
    }

    if (!errors.isEmpty()) {
      throw new ValidationException(errors);
    }
  }
}
