package io.github.dumijdev.restdb4j.server.adapters.output.database.utils.connection.builder;

public class MySQLConnectionStringBuilder implements ConnectionStringBuilder {
  private final String host;
  private final String port;
  private final String dbName;

  public MySQLConnectionStringBuilder(String host, String port, String dbName) {
    this.host = host;
    this.port = port;
    this.dbName = dbName;
  }

  @Override
  public String generate() {
    return String.format("jdbc:mysql://%s:%s/%s", host, port, dbName);
  }
}
