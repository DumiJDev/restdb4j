package io.github.dumijdev.restdb4j.server.adapters.output.database.utils.connection.builder;

public class PostgresConnectionStringBuilder implements ConnectionStringBuilder {
  private final String host;
  private final String port;
  private final String dbName;

  public PostgresConnectionStringBuilder(String host, String port, String dbName) {
    this.host = host;
    this.port = port;
    this.dbName = dbName;
  }

  @Override
  public String generate() {
    return String.format("jdbc:postgresql://%s:%s/%s", host, port, dbName);
  }
}
