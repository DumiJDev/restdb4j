package io.github.dumijdev.restdb4j.server.adapters.input.controllers;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ConnectionControllerDocs {
  ResponseEntity<String> createConnection();
  ResponseEntity<List<String>> getConnections();
  ResponseEntity<String> getConnectionDetails();
  ResponseEntity<String> deleteConnection();
  ResponseEntity<String> updateConnection();
}
