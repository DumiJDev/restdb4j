package io.github.dumijdev.restdb4j.server.adapters.output.database.utils;

import io.github.dumijdev.restdb4j.server.adapters.output.database.utils.connection.builder.*;
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
      throw new IllegalArgumentException("Connection parameters cannot be empty");
    }

    String host = params.getOrDefault("host", "localhost");
    String port = params.getOrDefault("port", getDefaultPort(database));
    String dbName = params.getOrDefault("database", "");
    String user = params.getOrDefault("user", "");
    String password = params.getOrDefault("password", "");
    String url = params.getOrDefault("url", "");

    if (!url.isEmpty()) {
      log.info("Generated connection string from URL.");
      return url;
    }

    ConnectionStringBuilder connectionStringBuilder = switch (database) {
      case MYSQL -> new MySQLConnectionStringBuilder(host, port, dbName);
      case POSTGRES -> new PostgresConnectionStringBuilder(host, port, dbName);
      case ORACLE -> new OracleConnectionStringBuilder(host, port, dbName, user, password);
      case SQLITE -> new SQLiteConnectionStringBuilder(dbName);
      case H2 -> new H2ConnectionStringBuilder(dbName);
      default -> throw new UnsupportedOperationException("Database not supported: " + database);
    };

    logConnectionDetails(database, host, port, dbName, user);

    return connectionStringBuilder.generate();
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

  private void logConnectionDetails(SQLGenerator.Database database, String host, String port, String dbName, String user) {
    log.info("Generated connection string details: [Database: {}, Host: {}, Port: {}, Database Name: {}, User: {}]",
        database, host, port, dbName, maskString(user));
  }

  private String maskString(String input) {
    if (input == null || input.isEmpty()) {
      return "N/A";
    }
    return input.replaceAll("\\.", "*");
  }
}
