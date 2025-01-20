package io.github.dumijdev.restdb4j.server.adapters.input.controllers;

import io.github.dumijdev.restdb4j.server.adapters.input.controllers.docs.ConnectionControllerDocs;
import io.github.dumijdev.restdb4j.server.adapters.input.controllers.models.connections.NewConnectionRequest;
import io.github.dumijdev.restdb4j.server.adapters.input.controllers.models.connections.UpdateConnectionRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/connections")
public class ConnectionController implements ConnectionControllerDocs {
  @Override
  public ResponseEntity<String> create(NewConnectionRequest request) {
    return null;
  }

  @Override
  public ResponseEntity<List<String>> getAll(int page, int size) {
    return null;
  }

  @Override
  public ResponseEntity<String> getOne(String name) {
    return null;
  }

  @Override
  public ResponseEntity<String> delete(String name) {
    return null;
  }

  @Override
  public ResponseEntity<String> update(UpdateConnectionRequest request) {
    return null;
  }
}
