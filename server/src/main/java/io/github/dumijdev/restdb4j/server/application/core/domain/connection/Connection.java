package io.github.dumijdev.restdb4j.server.application.core.domain.connection;

public record Connection(String name, String host, String port, String database, String jdbcUrl,
                         String user, String password, String jdbcClassDriver,
                         String platform) {
}
