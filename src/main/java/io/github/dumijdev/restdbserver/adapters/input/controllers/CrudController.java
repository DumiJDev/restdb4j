package io.github.dumijdev.restdbserver.adapters.input.controllers;

import io.github.dumijdev.restdbserver.adapters.input.controllers.factories.SelectParamsFactory;
import io.github.dumijdev.restdbserver.adapters.input.controllers.factories.SelectResponseFactory;
import io.github.dumijdev.restdbserver.adapters.input.controllers.models.SelectRequest;
import io.github.dumijdev.restdbserver.adapters.input.controllers.models.SelectResponse;
import io.github.dumijdev.restdbserver.application.core.domain.select.SelectItem;
import io.github.dumijdev.restdbserver.application.core.domain.select.SelectParams;
import io.github.dumijdev.restdbserver.application.ports.input.SelectOperationInputPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RequestMapping("/api/")
@RestController
public class CrudController implements CrudControllerDocs {
  private final SelectOperationInputPort selectOperation;

  public CrudController(SelectOperationInputPort selectOperation) {
    this.selectOperation = selectOperation;
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
  public ResponseEntity<Object> insert(@PathVariable String table) {
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
