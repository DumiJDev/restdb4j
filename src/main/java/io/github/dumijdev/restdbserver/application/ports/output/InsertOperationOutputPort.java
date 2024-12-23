package io.github.dumijdev.restdbserver.application.ports.output;

import io.github.dumijdev.restdbserver.application.core.domain.insert.InsertParams;
import io.github.dumijdev.restdbserver.application.core.domain.insert.InsertResult;

public interface InsertOperationOutputPort {
  InsertResult insert(InsertParams params);
}
