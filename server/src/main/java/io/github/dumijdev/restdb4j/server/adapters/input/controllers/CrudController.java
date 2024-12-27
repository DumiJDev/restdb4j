package io.github.dumijdev.restdb4j.server.adapters.input.controllers;

import io.github.dumijdev.restdb4j.server.adapters.input.controllers.factories.SelectParamsFactory;
import io.github.dumijdev.restdb4j.server.adapters.input.controllers.factories.SelectResponseFactory;
import io.github.dumijdev.restdb4j.server.adapters.input.controllers.models.InsertRequest;
import io.github.dumijdev.restdb4j.server.adapters.input.controllers.models.SelectRequest;
import io.github.dumijdev.restdb4j.server.adapters.input.controllers.models.SelectResponse;
import io.github.dumijdev.restdb4j.server.application.core.domain.insert.InsertParams;
import io.github.dumijdev.restdb4j.server.application.ports.input.InsertOperationInputPort;
import io.github.dumijdev.restdb4j.server.application.ports.input.SelectOperationInputPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/api/")
@RestController
public class CrudController implements CrudControllerDocs {
  private final SelectOperationInputPort selectOperation;
  private final InsertOperationInputPort insertOperation;

  public CrudController(SelectOperationInputPort selectOperation, InsertOperationInputPort insertOperation) {
    this.selectOperation = selectOperation;
    this.insertOperation = insertOperation;
  }

  @Override
  @PostMapping("/select/{table}")
  public ResponseEntity<SelectResponse> select(@PathVariable String table, @RequestBody SelectRequest request) {
    var selectResult = selectOperation.select(SelectParamsFactory.createFromRequest(request, table));

    var response = SelectResponseFactory.createFromSelectResult(selectResult);

    return ResponseEntity.ok(response);
  }

  @Override
  @PostMapping("/insert/{table}")
  public ResponseEntity<Object> insert(@PathVariable String table, @RequestBody InsertRequest request) {
    var insertResult = insertOperation.insert(new InsertParams(table, Optional.empty(), request.data(), Optional.empty()));

    return ResponseEntity.ok().build();
  }

  @Override
  @PostMapping("/update/{table}")
  public ResponseEntity<Object> update(@PathVariable String table) {
    return ResponseEntity.ok().build();
  }

  @Override
  @PostMapping("/delete/{table}")
  public ResponseEntity<Object> delete(@PathVariable String table) {
    return ResponseEntity.ok().build();
  }
}
