package io.github.dumijdev.restdb4j.server.application.ports.output;

import io.github.dumijdev.restdb4j.server.application.core.domain.update.UpdateParams;
import io.github.dumijdev.restdb4j.server.application.core.domain.update.UpdateResult;

public interface UpdateOperationOutputPort {
  UpdateResult update(UpdateParams params);
}
