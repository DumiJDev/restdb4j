package io.github.dumijdev.restdbserver.adapters.input.controllers.factories;

import io.github.dumijdev.restdbserver.adapters.input.controllers.models.OrderClause;
import io.github.dumijdev.restdbserver.adapters.input.controllers.models.SelectRequest;
import io.github.dumijdev.restdbserver.adapters.input.controllers.models.WhereCondition;
import io.github.dumijdev.restdbserver.application.core.domain.common.Where;
import io.github.dumijdev.restdbserver.application.core.domain.common.Where.Condition;
import io.github.dumijdev.restdbserver.application.core.domain.select.Order;
import io.github.dumijdev.restdbserver.application.core.domain.select.SelectParams;

import java.util.Set;
import java.util.stream.Collectors;

public class SelectParamsFactory {
  private static Order mapOrder(OrderClause orderClause) {
    return new Order(
        orderClause.field(),
        Order.Direction.from(orderClause.direction())
    );
  }

  public static SelectParams createFromRequest(SelectRequest request, String table) {
    var builder = SelectParams.builder();

    builder.fields(request.fields() != null ? request.fields() : Set.of());

    if (request.order() != null && !request.order().isEmpty()) {
      builder.order(request.order().stream().map(SelectParamsFactory::mapOrder).collect(Collectors.toSet()));
    }

    request.offset().ifPresent(builder::offset);
    request.limit().ifPresent(builder::limit);

    builder.schema(request.schema());
    builder.table(table);

    if (request.where() != null) {
      var where = request.where();
      builder.where(new Where(where.logic(),
          where.conditions().stream().map(SelectParamsFactory::mapWhere).collect(Collectors.toSet())));
    }

    return builder.build();
  }

  private static Condition mapWhere(WhereCondition condition) {
    return new Condition(condition.field(), Where.Operator.from(condition.operator()), condition.value());
  }
}
