package io.github.dumijdev.restdbserver.adapters.output.database.utils;

import io.github.dumijdev.restdbserver.application.core.domain.delete.DeleteParams;
import io.github.dumijdev.restdbserver.application.core.domain.insert.InsertParams;
import io.github.dumijdev.restdbserver.application.core.domain.select.SelectParams;
import io.github.dumijdev.restdbserver.application.core.domain.update.UpdateParams;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class SQLGenerator {
  private final DatabaseStrategy strategy;

  public SQLGenerator(Database database) {
    this.strategy = DatabaseStrategyFactory.getStrategy(database);
  }

  public String generateSelect(SelectParams params) {
    return strategy.generateSelect(params);
  }

  public String generateUpdate(UpdateParams params) {
    return strategy.generateUpdate(params);
  }

  public String generateInsert(InsertParams params) {
    return strategy.generateInsert(params);
  }

  public String generateDelete(DeleteParams params) {
    return strategy.generateDelete(params);
  }

  public enum Database {
    MYSQL("mysql"), POSTGRES("postgres"), ORACLE("oracle"),
    SQLITE("sqlite"), H2("h2"), UNKNOWN("unknown");

    private final String value;

    public String value() {
      return value;
    }

    Database(String value) {
      this.value = value;
    }

    public static Database from(String value) {
      Predicate<Database> filterBy = db -> db.value.equals(value);
      return Arrays.stream(values()).filter(filterBy).findFirst().orElse(UNKNOWN);
    }

    public static boolean validate(String value) {
      return Database.from(value) != UNKNOWN;
    }
  }

  private static final class DatabaseStrategyFactory {
    private static final Map<Database, DatabaseStrategy> STRATEGIES = new HashMap<>();

    static {
      STRATEGIES.put(Database.MYSQL, new MySQLStrategy());
      STRATEGIES.put(Database.POSTGRES, new PostgresStrategy());
      STRATEGIES.put(Database.ORACLE, new OracleStrategy());
      STRATEGIES.put(Database.SQLITE, new SQLiteStrategy());
      STRATEGIES.put(Database.H2, new H2Strategy());
      STRATEGIES.put(Database.UNKNOWN, new UnsupportedDatabaseStrategy());
    }

    public static DatabaseStrategy getStrategy(Database database) {
      return STRATEGIES.getOrDefault(database, new UnsupportedDatabaseStrategy());
    }
  }
}
