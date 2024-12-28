package io.github.dumijdev.restdb4j.server.adapters.input.controllers;

import io.github.dumijdev.restdb4j.server.adapters.input.controllers.factories.*;
import io.github.dumijdev.restdb4j.server.adapters.input.controllers.models.*;
import io.github.dumijdev.restdb4j.server.application.core.domain.insert.InsertParams;
import io.github.dumijdev.restdb4j.server.application.ports.input.DeleteOperationInputPort;
import io.github.dumijdev.restdb4j.server.application.ports.input.InsertOperationInputPort;
import io.github.dumijdev.restdb4j.server.application.ports.input.SelectOperationInputPort;
import io.github.dumijdev.restdb4j.server.application.ports.input.UpdateOperationInputPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/api/")
@RestController
public class CrudController implements CrudControllerDocs {
  private final SelectOperationInputPort selectOperation;
  private final InsertOperationInputPort insertOperation;
  private final UpdateOperationInputPort updateOperation;
  private final DeleteOperationInputPort deleteOperation;

  public CrudController(SelectOperationInputPort selectOperation,
                        InsertOperationInputPort insertOperation,
                        UpdateOperationInputPort updateOperation,
                        DeleteOperationInputPort deleteOperation) {
    this.selectOperation = selectOperation;
    this.insertOperation = insertOperation;
    this.updateOperation = updateOperation;
    this.deleteOperation = deleteOperation;
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
  public ResponseEntity<Void> insert(@PathVariable String table, @RequestBody InsertRequest request) {
    var insertResult = insertOperation.insert(InsertParamsFactory.createFromRequest(request, table));

    return ResponseEntity.noContent().build();
  }

  @Override
  @PostMapping("/update/{table}")
  public ResponseEntity<Void> update(@PathVariable String table, @RequestBody UpdateRequest request) {
    var updateResult = updateOperation.update(UpdateParamsFactory.createFromRequest(request, table));
    return ResponseEntity.noContent().build();
  }

  @Override
  @PostMapping("/delete/{table}")
  public ResponseEntity<Void> delete(@PathVariable String table, @RequestBody DeleteRequest request) {
    var deleteResult = deleteOperation.delete(DeleteParamsFactory.createFromRequest(request, table));
    return ResponseEntity.noContent().build();
  }
}
