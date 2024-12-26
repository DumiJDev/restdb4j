package io.github.dumijdev.restdb4j.server.adapters.output.database.utils;

import io.github.dumijdev.restdb4j.server.application.core.domain.delete.DeleteParams;
import io.github.dumijdev.restdb4j.server.application.core.domain.insert.InsertParams;
import io.github.dumijdev.restdb4j.server.application.core.domain.select.SelectParams;
import io.github.dumijdev.restdb4j.server.application.core.domain.update.UpdateParams;

public interface DatabaseStrategy {
  String generateSelect(SelectParams params);

  String generateInsert(InsertParams params);

  String generateUpdate(UpdateParams params);

  String generateDelete(DeleteParams params);
}
