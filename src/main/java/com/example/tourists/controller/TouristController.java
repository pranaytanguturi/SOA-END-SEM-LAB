package com.example.tourists.controller;

import com.example.tourists.model.Tourist;
import com.example.tourists.service.TouristService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tourists")
@Tag(name = "Tourists", description = "Token-protected tourist records API")
@SecurityRequirement(name = "Bearer Authentication")
public class TouristController {
    private final TouristService service;
    public TouristController(TouristService service) { this.service = service; }

    @GetMapping
    @Operation(summary = "Retrieve all registered tourists")
    public List<Tourist> getAllTourists() { return service.findAll(); }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieve one tourist by ID")
    public Tourist getTourist(@PathVariable Long id) { return service.findById(id); }

    // Compatibility with the requested route/method: this PUT route is a read-only retrieval alias.
    // Use GET for normal REST retrieval; use PUT /{id} with a body to update a record.
    @PutMapping(value = "/{id}", headers = "X-Action=fetch")
    @Operation(summary = "Retrieve one tourist by ID (PUT alias; send header X-Action: fetch)")
    public Tourist fetchTouristUsingPut(@PathVariable Long id) { return service.findById(id); }

    @PutMapping(value = "/{id}", headers = "!X-Action=fetch")
    @Operation(summary = "Update a tourist by ID")
    public Tourist updateTourist(@PathVariable Long id, @RequestBody Tourist tourist) {
        return service.update(id, tourist);
    }
}
