package io.github.dumijdev.restdb4j.server.adapters.input.controllers.docs;

import io.github.dumijdev.restdb4j.server.adapters.input.controllers.models.connections.NewConnectionRequest;
import io.github.dumijdev.restdb4j.server.adapters.input.controllers.models.connections.UpdateConnectionRequest;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@OpenAPIDefinition(
    info = @Info(version = "0.0.1", title = "${spring.application.name} Connections", license = @License(name = "MIT"), contact = @Contact(name = "DumiJDev", email = "dumi703@gmail.com")),
    tags = {@Tag(name = "RestDB", description = "Connections to database")}
)
public interface ConnectionControllerDocs {
  ResponseEntity<String> create(NewConnectionRequest request);
  ResponseEntity<List<String>> getAll(int page, int size);
  ResponseEntity<String> getOne(String name);
  ResponseEntity<String> delete(String name);
  ResponseEntity<String> update(UpdateConnectionRequest request);
}
