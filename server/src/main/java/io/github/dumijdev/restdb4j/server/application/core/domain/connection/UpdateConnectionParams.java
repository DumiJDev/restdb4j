package io.github.dumijdev.restdb4j.server.application.core.domain.connection;

import java.util.Optional;

public record UpdateConnectionParams(String name, String host, String port, String database, Optional<String> jdbcUrl,
                                     String user, String password, Optional<String> jdbcClassDriver,
                                     String platform) {
}
