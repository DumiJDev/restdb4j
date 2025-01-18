package io.github.dumijdev.restdb4j.server.adapters.output.database.utils.connection.builder;

@FunctionalInterface
public interface ConnectionStringBuilder {
  String generate();
}
