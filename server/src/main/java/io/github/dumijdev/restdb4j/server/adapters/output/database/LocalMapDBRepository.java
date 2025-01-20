package io.github.dumijdev.restdb4j.server.adapters.output.database;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.dumijdev.restdb4j.server.application.core.domain.connection.Connection;
import org.jetbrains.annotations.NotNull;
import org.mapdb.DB;
import org.mapdb.DataInput2;
import org.mapdb.DataOutput2;
import org.mapdb.Serializer;
import org.mapdb.serializer.SerializerString;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class LocalMapDBRepository implements RestD4JRepository {
  private final DB db;
  private final Map<String, Connection> connections;

  public LocalMapDBRepository(DB db, ObjectMapper objectMapper) {
    this.db = db;
    this.connections = db.hashMap("connections", new SerializerString(), new SerializerConnectionClass(objectMapper)).createOrOpen();
  }

  @Override
  public List<Connection> findAll() {
    return connections.values().stream().toList();
  }

  @Override
  public Optional<Connection> findById(String id) {
    return Optional.ofNullable(connections.get(id));
  }

  @Override
  public Connection save(Connection connection) {
    if (!connections.containsKey(connection.name()))
      connections.put(connection.name(), connection);
    else {
      connections.computeIfPresent(connection.name(), (k, con) -> new Connection(
          con.name(),
          connection.host() == null ? con.host() : connection.host(),
          connection.port() == null ? con.port() : connection.port(),
          connection.database() == null ? con.database() : connection.database(),
          connection.jdbcUrl() == null ? con.jdbcUrl() : connection.jdbcUrl(),
          connection.user() == null ? con.user() : connection.user(),
          connection.password() == null ? con.password() : connection.password(),
          connection.jdbcClassDriver() == null ? con.jdbcClassDriver() : connection.jdbcClassDriver(),
          connection.platform() == null ? con.platform() : connection.platform()
      ));
    }

    db.commit();

    return connection;
  }

  @Override
  public void deleteById(String id) {
    connections.remove(id);
    db.commit();
  }

  private record SerializerConnectionClass(ObjectMapper objectMapper) implements Serializer<Connection> {

    @Override
    public void serialize(@NotNull DataOutput2 dataOutput2, @NotNull Connection connection) throws IOException {
      dataOutput2.writeUTF(objectMapper.writeValueAsString(connection));
    }

    @Override
    public Connection deserialize(@NotNull DataInput2 dataInput2, int i) throws IOException {
      return objectMapper.readValue(dataInput2.readUTF(), Connection.class);
    }
  }
}
