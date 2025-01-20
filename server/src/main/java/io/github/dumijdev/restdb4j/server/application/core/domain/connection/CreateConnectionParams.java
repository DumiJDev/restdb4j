package io.github.dumijdev.restdb4j.server.application.core.domain.connection;

import java.util.Optional;

import static java.util.Optional.ofNullable;

public record CreateConnectionParams(String name, String host, String port, String database, Optional<String> jdbcUrl,
                                     String user, String password, Optional<String> jdbcClassDriver,
                                     String platform) {


  public static CreateConnectionParamsBuilder builder() {
    return new CreateConnectionParamsBuilder();
  }

  public static final class CreateConnectionParamsBuilder {
    private String name;
    private String host;
    private String port;
    private String database;
    private String jdbcUrl;
    private String user;
    private String password;
    private String jdbcClassDriver;
    private String platform;

    private CreateConnectionParamsBuilder() {
    }

    public CreateConnectionParamsBuilder name(String name) {
      this.name = name;
      return this;
    }

    public CreateConnectionParamsBuilder host(String host) {
      this.host = host;
      return this;
    }

    public CreateConnectionParamsBuilder port(String port) {
      this.port = port;
      return this;
    }

    public CreateConnectionParamsBuilder database(String database) {
      this.database = database;
      return this;
    }

    public CreateConnectionParamsBuilder jdbcUrl(String jdbcUrl) {
      this.jdbcUrl = jdbcUrl;
      return this;
    }

    public CreateConnectionParamsBuilder user(String user) {
      this.user = user;
      return this;
    }

    public CreateConnectionParamsBuilder password(String password) {
      this.password = password;
      return this;
    }

    public CreateConnectionParamsBuilder jdbcClassDriver(String jdbcClassDriver) {
      this.jdbcClassDriver = jdbcClassDriver;
      return this;
    }

    public CreateConnectionParamsBuilder platform(String platform) {
      this.platform = platform;
      return this;
    }

    public CreateConnectionParams build() {
      return new CreateConnectionParams(name, host, port, database, ofNullable(jdbcUrl), user, password, ofNullable(jdbcClassDriver), platform);
    }
  }
}
