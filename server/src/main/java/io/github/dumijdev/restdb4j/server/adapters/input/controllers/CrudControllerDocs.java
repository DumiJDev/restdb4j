package io.github.dumijdev.restdb4j.server.adapters.input.controllers;

import io.github.dumijdev.restdb4j.server.adapters.input.controllers.models.InsertRequest;
import io.github.dumijdev.restdb4j.server.adapters.input.controllers.models.SelectRequest;
import io.github.dumijdev.restdb4j.server.adapters.input.controllers.models.SelectResponse;
import io.github.dumijdev.restdb4j.server.application.core.domain.exceptions.ValidationException;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;


@OpenAPIDefinition(
    info = @Info(version = "0.0.1", license = @License(name = "MIT"), contact = @Contact(name = "DumiJDev", email = "dumi703@gmail.com")),
    tags = {@Tag(name = "RestDB", description = "Operations from database")}
)
public interface CrudControllerDocs {
  @Operation(summary = "Retrieve data")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Retrieve a set of data from a table in a database",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = SelectResponse.class))),
      @ApiResponse(responseCode = "400", description = "Invalid request body",
          content = @Content(mediaType = "application/json",
              schema = @Schema(allOf = ValidationException.ItemException.class))
      )})
  @PostMapping("/select/{table}")
  ResponseEntity<SelectResponse> select(@PathVariable String table, SelectRequest request);

  @Operation(summary = "Insert data into database")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Retrieve a set of data from a table in a database",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = SelectResponse.class))),
      @ApiResponse(responseCode = "400", description = "Invalid request body",
          content = @Content(mediaType = "application/json",
              schema = @Schema(allOf = ValidationException.ItemException.class))
      )})
  @PostMapping("/insert/{table}")
  ResponseEntity<Object> insert(@PathVariable String table, @RequestBody InsertRequest request);

  @Operation(summary = "Update data into database")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Retrieve a set of data from a table in a database",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = SelectResponse.class))),
      @ApiResponse(responseCode = "400", description = "Invalid request body",
          content = @Content(mediaType = "application/json",
              schema = @Schema(allOf = ValidationException.ItemException.class))
      )})
  @PostMapping("/update/{table}")
  ResponseEntity<Object> update(@PathVariable String table);

  @Operation(summary = "Delete data from database")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Retrieve a set of data from a table in a database",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = SelectResponse.class))),
      @ApiResponse(responseCode = "400", description = "Invalid request body",
          content = @Content(mediaType = "application/json",
              schema = @Schema(allOf = ValidationException.ItemException.class))
      )})
  @PostMapping("/delete/{table}")
  ResponseEntity<Object> delete(@PathVariable String table);
}
