package io.github.dumijdev.restdb4j.server.application.ports.input;

import io.github.dumijdev.restdb4j.server.application.core.domain.insert.InsertParams;
import io.github.dumijdev.restdb4j.server.application.core.domain.insert.InsertResult;

public interface InsertOperationInputPort {
  InsertResult insert(InsertParams params);
}
