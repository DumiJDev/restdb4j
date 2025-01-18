package io.github.dumijdev.restdb4j.server.adapters.output.database.utils.connection.builder;

public class OracleConnectionStringBuilder implements ConnectionStringBuilder {

  private final String host;
  private final String port;
  private final String dbName;
  private final String user;
  private final String password;

  public OracleConnectionStringBuilder(String host, String port, String dbName, String user, String password) {
    this.host = host;
    this.port = port;
    this.dbName = dbName;
    this.user = user;
    this.password = password;
  }

  @Override
  public String generate() {
    return String.format("jdbc:oracle:thin:%s/%s@%s:%s:%s", user, password, host, port, dbName);
  }
}
