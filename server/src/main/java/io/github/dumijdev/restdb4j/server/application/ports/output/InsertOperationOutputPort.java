package io.github.dumijdev.restdb4j.server.application.ports.output;

import io.github.dumijdev.restdb4j.server.application.core.domain.insert.InsertParams;
import io.github.dumijdev.restdb4j.server.application.core.domain.insert.InsertResult;

public interface InsertOperationOutputPort {
  InsertResult insert(InsertParams params);
}
