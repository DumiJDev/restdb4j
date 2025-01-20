package io.github.dumijdev.restdb4j.server.infra.config;

import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class MapDBConfig {
  private final Path dbPath = Paths.get(System.getProperty("user.dir"), ".restdb4j", "databases", "data.db");

  @Bean
  public DB restdb4jDB() throws IOException {
    if (!Files.exists(dbPath.getParent())) {
      Files.createDirectories(dbPath.getParent());
    }

    return DBMaker.fileDB(dbPath.toFile())
        .transactionEnable()
        .concurrencyScale(16)
        .closeOnJvmShutdown()
        .checksumHeaderBypass()
        .make();
  }

}
