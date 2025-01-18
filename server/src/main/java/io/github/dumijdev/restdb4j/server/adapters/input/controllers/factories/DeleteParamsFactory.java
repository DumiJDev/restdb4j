package io.github.dumijdev.restdb4j.server.adapters.input.controllers.factories;

import io.github.dumijdev.restdb4j.server.adapters.input.controllers.models.DeleteRequest;
import io.github.dumijdev.restdb4j.server.application.core.domain.common.Where;
import io.github.dumijdev.restdb4j.server.application.core.domain.delete.DeleteParams;

import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

public class DeleteParamsFactory {
  public static DeleteParams createFromRequest(DeleteRequest request, String table) {
    return new DeleteParams(table,
        ofNullable(request.alias()),
        ofNullable(request.schema()),
        mapWhere(request),
        ofNullable(request.sqlQuery())
    );
  }

  private static Optional<Where> mapWhere(DeleteRequest request) {
    if (request.where() == null) {
      return Optional.empty();
    }
    var where = request.where();

    var conditions = where.conditions().stream().map(condition -> new Where.Condition(
        condition.field(),
        ofNullable(condition.table()),
        Where.Operator.from(condition.operator()),
        condition.value()
    )).collect(Collectors.toSet());

    return Optional.of(new Where(request.where().logic(), conditions));
  }
}
