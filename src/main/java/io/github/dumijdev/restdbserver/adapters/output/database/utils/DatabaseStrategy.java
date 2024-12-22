package io.github.dumijdev.restdbserver.adapters.output.database.utils;

import io.github.dumijdev.restdbserver.application.core.domain.delete.DeleteParams;
import io.github.dumijdev.restdbserver.application.core.domain.insert.InsertParams;
import io.github.dumijdev.restdbserver.application.core.domain.select.SelectParams;
import io.github.dumijdev.restdbserver.application.core.domain.update.UpdateParams;

public interface DatabaseStrategy {
  String generateSelect(SelectParams params);

  String generateInsert(InsertParams params);

  String generateUpdate(UpdateParams params);

  String generateDelete(DeleteParams params);
}
