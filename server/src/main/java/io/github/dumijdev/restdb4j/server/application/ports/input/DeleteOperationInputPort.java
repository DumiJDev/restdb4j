package io.github.dumijdev.restdb4j.server.application.ports.input;

import io.github.dumijdev.restdb4j.server.application.core.domain.delete.DeleteParams;
import io.github.dumijdev.restdb4j.server.application.core.domain.delete.DeleteResult;

public interface DeleteOperationInputPort {
  DeleteResult delete(DeleteParams params);
}
