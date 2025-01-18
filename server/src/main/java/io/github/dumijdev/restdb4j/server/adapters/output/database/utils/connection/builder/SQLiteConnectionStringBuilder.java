package io.github.dumijdev.restdb4j.server.adapters.output.database.utils.connection.builder;

public class SQLiteConnectionStringBuilder implements ConnectionStringBuilder {
  private final String dbName;

  public SQLiteConnectionStringBuilder(String dbName) {
    this.dbName = dbName;
  }

  @Override
  public String generate() {
    return String.format("jdbc:sqlite:/restdb/%s", dbName);
  }
}
