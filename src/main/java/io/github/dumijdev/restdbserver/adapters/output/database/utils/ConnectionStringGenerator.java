package io.github.dumijdev.restdbserver.adapters.output.database.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class ConnectionStringGenerator {
  private final SQLGenerator.Database database;

  public ConnectionStringGenerator(SQLGenerator.Database database) {
    this.database = database;
  }

  public String generate(Map<String, String> params) {
    if (params == null || params.isEmpty()) {
      throw new IllegalArgumentException("Os parâmetros de conexão não podem ser nulos ou vazios.");
    }

    String host = params.getOrDefault("host", "localhost");
    String port = params.getOrDefault("port", getDefaultPort(database));
    String dbName = params.getOrDefault("database", "");
    String user = params.getOrDefault("user", "");
    String password = params.getOrDefault("password", "");
    String url = params.getOrDefault("url", "");

    if (!url.isEmpty()) {
      return url;
    }

    var connectionString =  switch (database) {
      case MYSQL -> generateMySQLConnectionString(host, port, dbName, user, password);
      case POSTGRES -> generatePostgresConnectionString(host, port, dbName, user, password);
      case ORACLE -> generateOracleConnectionString(host, port, dbName, user, password);
      case SQLITE -> generateSQLiteConnectionString(dbName);
      case H2 -> generateH2ConnectionString(dbName);
      default -> throw new UnsupportedOperationException("Database not supported: " + database);
    };

    log.info("Generated connection string: {}", connectionString);

    return connectionString;
  }

  private String getDefaultPort(SQLGenerator.Database database) {
    return switch (database) {
      case MYSQL -> "3306";
      case POSTGRES -> "5432";
      case ORACLE -> "1521";
      case SQLITE, H2 -> "";
      default -> throw new UnsupportedOperationException("Database not supported: " + database);
    };
  }

  private String generateMySQLConnectionString(String host, String port, String dbName, String user, String password) {
    return String.format("jdbc:mysql://%s:%s/%s?user=%s&password=%s", host, port, dbName, user, password);
  }

  private String generatePostgresConnectionString(String host, String port, String dbName, String user, String password) {
    return String.format("jdbc:postgresql://%s:%s/%s?user=%s&password=%s", host, port, dbName, user, password);
  }

  private String generateOracleConnectionString(String host, String port, String dbName, String user, String password) {
    return String.format("jdbc:oracle:thin:%s/%s@%s:%s:%s", user, password, host, port, dbName);
  }

  private String generateSQLiteConnectionString(String dbName) {
    return String.format("jdbc:sqlite:%s", dbName);
  }

  private String generateH2ConnectionString(String dbName) {
    return String.format("jdbc:h2:file:/restdb/%s", dbName);
  }
}
