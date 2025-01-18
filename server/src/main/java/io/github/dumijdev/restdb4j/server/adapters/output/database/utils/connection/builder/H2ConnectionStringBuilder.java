package io.github.dumijdev.restdb4j.server.adapters.output.database.utils.connection.builder;

public class H2ConnectionStringBuilder implements ConnectionStringBuilder {
  private final String dbName;

  public H2ConnectionStringBuilder(String dbName) {
    this.dbName = dbName;
  }

  @Override
  public String generate() {
    return String.format("jdbc:h2:file:/restdb/%s", dbName);
  }
}
