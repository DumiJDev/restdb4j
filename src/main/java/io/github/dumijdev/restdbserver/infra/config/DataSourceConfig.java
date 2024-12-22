package io.github.dumijdev.restdbserver.infra.config;

import io.github.dumijdev.restdbserver.adapters.output.database.utils.ConnectionStringGenerator;
import io.github.dumijdev.restdbserver.adapters.output.database.utils.SQLGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Map;
import java.util.Optional;

import static io.github.dumijdev.restdbserver.adapters.output.database.utils.SQLGenerator.Database.H2;


@Configuration
public class DataSourceConfig {
  @Value("${restdb4j.user}")
  private String username;
  @Value("${restdb4j.password}")
  private String password;
  @Value("${restdb4j.host}")
  private String host;
  @Value("${restdb4j.port}")
  private String port;
  @Value("${restdb4j.platform}")
  private String platform;
  @Value("${restdb4j.database}")
  private String databaseName;
  @Value("${restdb4j.driver-class}")
  private String driverClassName;
  @Value("${restdb4j.url}")
  private String url;


  private static final Map<String, String> DRIVER_MAP = Map.of(
      "mysql", "com.mysql.cj.jdbc.Driver",
      "postgresql", "org.postgresql.Driver",
      "oracle", "oracle.jdbc.OracleDriver",
      "sqlite", "org.sqlite.JDBC",
      "h2", "org.h2.Driver"
  );

  @Bean
  public DataSource dataSource() {
    validateProperties();

    var database = SQLGenerator.Database.from(platform);
    var resolvedDriverClass = resolveDriverClass(database);
    if (resolvedDriverClass == null) {
      throw new IllegalStateException("JDBC driver class not found: " + database.value());
    }

    var data = getData();

    var dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(resolvedDriverClass);
    dataSource.setUrl(new ConnectionStringGenerator(database).generate(data));
    dataSource.setUsername(Optional.ofNullable(username).orElseGet(() -> database == H2 ? "sa" : ""));
    dataSource.setPassword(Optional.ofNullable(password).orElse(""));

    return dataSource;
  }

  private String resolveDriverClass(SQLGenerator.Database database) {
    if (driverClassName != null && !driverClassName.isEmpty()) {
      return driverClassName;
    }

    return DRIVER_MAP.getOrDefault(database.value(), null);
  }

  private Map<String, String> getData() {
    return Map.of(
        "host", host,
        "port", port,
        "database", databaseName,
        "user", Optional.ofNullable(username).orElse(""),
        "password", Optional.ofNullable(password).orElse(""),
        "url", url
    );
  }

  private void validateProperties() {
    if (host == null || host.isEmpty()) {
      throw new IllegalArgumentException("The 'host' property is required.");
    }

    if (platform == null || platform.isEmpty()) {
      throw new IllegalArgumentException("The 'platform' property is required.");
    }
    if (databaseName == null || databaseName.isEmpty()) {
      throw new IllegalArgumentException("The 'database' property is required.");
    }
  }

}
