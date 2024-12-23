package io.github.dumijdev.restdbserver.application.ports.input;

import io.github.dumijdev.restdbserver.application.core.domain.insert.InsertParams;
import io.github.dumijdev.restdbserver.application.core.domain.insert.InsertResult;

public interface InsertOperationInputPort {
  InsertResult insert(InsertParams params);
}
