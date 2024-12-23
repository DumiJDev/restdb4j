package io.github.dumijdev.restdbserver.adapters.input.controllers.factories;

import io.github.dumijdev.restdbserver.adapters.input.controllers.models.JoinSpec;
import io.github.dumijdev.restdbserver.adapters.input.controllers.models.OrderClauseSpec;
import io.github.dumijdev.restdbserver.adapters.input.controllers.models.SelectRequest;
import io.github.dumijdev.restdbserver.adapters.input.controllers.models.WhereConditionSpec;
import io.github.dumijdev.restdbserver.application.core.domain.common.Where;
import io.github.dumijdev.restdbserver.application.core.domain.common.Where.Condition;
import io.github.dumijdev.restdbserver.application.core.domain.common.Where.Operator;
import io.github.dumijdev.restdbserver.application.core.domain.select.*;

import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

public class SelectParamsFactory {

  public static SelectParams createFromRequest(SelectRequest request, String table) {
    var builder = SelectParams.builder();

    builder.sql(request.sqlQuery());

    builder.alias(request.alias());

    if (request.fields() != null) {
      var fields = request.fields().stream();

      builder.fields(fields.map(fieldSpec -> new SelectField(fieldSpec.name(), ofNullable(request.alias()), fieldSpec.alias()))
          .collect(Collectors.toSet()));
    } else {
      builder.fields(Set.of());
    }

    if (request.joins() != null) {
      var joins = request.joins().stream();
      builder.joins(
          joins.map(SelectParamsFactory::mapJoin)
              .collect(Collectors.toSet())
      );
    }

    if (request.order() != null && !request.order().isEmpty()) {
      builder.order(request.order().stream().map(SelectParamsFactory::mapOrder).collect(Collectors.toSet()));
    }

    if (request.pagination() != null) {
      request.pagination().offset().ifPresent(builder::offset);
      request.pagination().limit().ifPresent(builder::limit);
    }

    builder.schema(request.schema());
    builder.table(table);

    if (request.where() != null) {
      var where = request.where();
      builder.where(new Where(where.logic(),
          where.conditions().stream().map(SelectParamsFactory::mapWhere).collect(Collectors.toSet())));
    }

    return builder.build();
  }

  private static Condition mapWhere(WhereConditionSpec condition) {
    return new Condition(
        condition.field(),
        ofNullable(condition.table()),
        Operator.from(condition.operator()),
        condition.value()
    );
  }

  private static SelectJoins mapJoin(JoinSpec joinSpec) {
    return new SelectJoins(
        JoinType.from(joinSpec.type()),
        joinSpec.table(),
        joinSpec.alias(),
        joinSpec.on() == null ? Set.of() :
            joinSpec.on().stream().map(onConditionSpec -> {
              SelectOnSide left = null;
              SelectOnSide right = null;

              if (onConditionSpec.left() != null) {
                left = new SelectOnSide(
                    onConditionSpec.left().table(),
                    onConditionSpec.left().field()
                );
              }

              if (onConditionSpec.right().isPresent()) {
                right = new SelectOnSide(
                    onConditionSpec.right().get().table(),
                    onConditionSpec.right().get().field()
                );
              }

              return new SelectOnCondition(
                  left,
                  ofNullable(right),
                  Operator.from(onConditionSpec.operator()),
                  ofNullable(onConditionSpec.value())
              );
            }).collect(Collectors.toSet())
    );
  }

  private static Order mapOrder(OrderClauseSpec orderClauseSpec) {
    return new Order(
        orderClauseSpec.field(),
        Order.Direction.from(orderClauseSpec.direction())
    );
  }
}
