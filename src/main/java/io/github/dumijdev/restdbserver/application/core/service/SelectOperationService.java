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

    if (params.where().isPresent()) {
      var where = params.where().get();

      if (where.symbol() != null && !Where.Symbol.validate(where.symbol())) {
        errors.add(new ItemException("symbol", "Invalid value"));
      }
      var i = 0;
      for (var condition : where.conditions()) {
        if (condition.field() == null) {
          errors.add(new ItemException("condition[%d]".formatted(i), "Field cannot be null"));
        }

        if (condition.operator() == null) {
          errors.add(new ItemException("condition[%d]".formatted(i), "Operator cannot be null"));
        }

        var operator = condition.operator();
        var isUnary = operator != NOT && operator != NOT_NULL && operator != NULL;

        if (condition.value() == null && isUnary) {
          errors.add(new ItemException("condition[%d]".formatted(i), "Value cannot be null"));
        }

        i++;
      }
    }

    if (!errors.isEmpty()) {
      throw new ValidationException(errors);
    }
  }
}
