package io.github.dumijdev.restdb4j.server.adapters.output.database.utils;

import io.github.dumijdev.restdb4j.server.application.core.domain.delete.DeleteParams;
import io.github.dumijdev.restdb4j.server.application.core.domain.insert.InsertParams;
import io.github.dumijdev.restdb4j.server.application.core.domain.select.SelectParams;
import io.github.dumijdev.restdb4j.server.application.core.domain.update.UpdateParams;

public class UnsupportedDatabaseStrategy implements DatabaseStrategy {
  @Override
  public String generateSelect(SelectParams params) {
    throw new UnsupportedOperationException("Database not supported");
  }

  @Override
  public String generateInsert(InsertParams params) {
    throw new UnsupportedOperationException("Database not supported");
  }

  @Override
  public String generateUpdate(UpdateParams params) {
    throw new UnsupportedOperationException("Database not supported");
  }

  @Override
  public String generateDelete(DeleteParams params) {
    throw new UnsupportedOperationException("Database not supported");
  }
}
