package io.github.dumijdev.restdbserver.controllers;

import io.github.dumijdev.restdbserver.controllers.dtos.NewTable;
import io.github.dumijdev.restdbserver.models.Table;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.*;

@RequestMapping("/api/${spring.application.name}/tables")
@RestController
public class TableController {
    @Value("${spring.application.name}")
    private String appName;
    private final static Map<String, Table> tables = new HashMap<>();

    static {
        var id1 = UUID.randomUUID().toString();
        tables.put(id1, new Table(id1, "tables"));
    }

    @GetMapping
    public ResponseEntity<Collection<Table>> getAll(@RequestParam(name = "name", required = false) String name) {
        return ResponseEntity.ok(tables.values());
    }

    @GetMapping("/{name}")
    public ResponseEntity<Table> getOne(@PathVariable String name) {
        if (!tables.containsKey(name))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(tables.get(name));
    }

    @PostMapping
    public ResponseEntity<Table> create(@RequestBody NewTable table) {
        if (tables.containsKey(table.name()))
            return ResponseEntity.badRequest().build();

        var id = UUID.randomUUID().toString();

        tables.put(id, new Table(id, table.name()));

        return ResponseEntity.created(URI.create("/api/%s/tables/%s".formatted(appName, id))).build();
    }
}
