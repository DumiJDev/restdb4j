package io.github.dumijdev.restdb4j.server.adapters.input.controllers.factories;

import io.github.dumijdev.restdb4j.server.adapters.input.controllers.models.InsertRequest;
import io.github.dumijdev.restdb4j.server.application.core.domain.insert.InsertParams;

import static java.util.Optional.ofNullable;

public class InsertParamsFactory {
  public static InsertParams createFromRequest(InsertRequest request, String table) {
    return new InsertParams(table, ofNullable(request.schema()), request.data(), ofNullable(request.sqlQuery()));
  }
}
