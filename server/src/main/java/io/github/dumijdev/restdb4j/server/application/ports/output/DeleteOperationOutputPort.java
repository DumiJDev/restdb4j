package io.github.dumijdev.restdb4j.server.application.ports.output;

import io.github.dumijdev.restdb4j.server.application.core.domain.delete.DeleteParams;
import io.github.dumijdev.restdb4j.server.application.core.domain.delete.DeleteResult;

public interface DeleteOperationOutputPort {
  DeleteResult update(DeleteParams params);
}
