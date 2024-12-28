package io.github.dumijdev.restdb4j.server.application.ports.input;

import io.github.dumijdev.restdb4j.server.application.core.domain.update.UpdateParams;
import io.github.dumijdev.restdb4j.server.application.core.domain.update.UpdateResult;

public interface UpdateOperationInputPort {
  UpdateResult update(UpdateParams params);
}
