package com.devops.backend.controller;

import com.devops.backend.model.Address;
import com.devops.backend.model.Warehouse;
import com.devops.backend.service.WarehouseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api/warehouse", produces = "application/json")
public class WarehouseController {

    private final WarehouseService warehouseService;

    @Autowired
    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @Tag(name = "Company GET Endpoints / Admin GET Endpoints")
    @Operation(summary = "Get all warehouses")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Warehouses retrieved"),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping(value="/")
    public ResponseEntity<List<Warehouse>>getAllWarehouses() {
        List<Warehouse> warehouses=warehouseService.getAllWarehouses();
        return ResponseEntity.ok(warehouses);
    }

    @Tag(name = "Company GET Endpoints / Admin GET Endpoints")
    @Operation(summary = "Get a warehouse by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Warehouse retrieved"),
            @ApiResponse(responseCode = "404", description = "Warehouse ID not valid", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping(value="/{warehouseId}")
    public ResponseEntity<Warehouse> getWarehouseById(@PathVariable Long warehouseId) {
        Warehouse warehouse = warehouseService.getWarehouseById(warehouseId);
        return ResponseEntity.ok(warehouse);
    }

    @Tag(name="Company PUT Endpoints / Admin PUT Endpoints")
    @Operation(summary = "Update a warehouse address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Warehouse address updated"),
            @ApiResponse(responseCode = "400", description = "Invalid address", content = @Content),
            @ApiResponse(responseCode = "404", description = "Warehouse ID not valid", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PutMapping(value="/{warehouseId}/address",consumes = "application/json")
    public ResponseEntity<Warehouse> updateWarehouseAddress(@PathVariable Long warehouseId, @RequestBody Address address) {
        if (ControllerValidator.isValidAddress(address)) {
            Warehouse updatedWarehouse = warehouseService.updateWarehouseAddress(warehouseId, address);
            return ResponseEntity.ok(updatedWarehouse);
        }
        return ResponseEntity.badRequest().build();
    }

    @Tags({
            @Tag(name = "Company PUT Endpoints / Admin PUT Endpoints"),
            @Tag(name = "Driver PUT Endpoints")
    })
    @Operation(summary = "Add a package to a warehouse")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Package added to warehouse"),
            @ApiResponse(responseCode = "404", description = "Warehouse or package ID not valid", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PutMapping(value="/{warehouseId}/{deliveryId}")
    public ResponseEntity<Warehouse> addPackage(@PathVariable Long warehouseId, @PathVariable Long deliveryId) {
        Warehouse updatedWarehouse = warehouseService.addPackage(warehouseId, deliveryId);
        return ResponseEntity.ok(updatedWarehouse);
    }

    @Tags({
            @Tag(name = "Company DELETE Endpoints / Admin DELETE Endpoints"),
            @Tag(name = "Driver DELETE Endpoints")
    })
    @Operation(summary = "Remove a package from a warehouse")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Package removed from warehouse"),
            @ApiResponse(responseCode = "404", description = "Warehouse or package ID not valid", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @DeleteMapping(value="/{warehouseId}/{deliveryId}")
    public ResponseEntity<Warehouse> removePackage(@PathVariable Long warehouseId, @PathVariable Long deliveryId) {
        Warehouse updatedWarehouse = warehouseService.removePackage(warehouseId, deliveryId);
        return ResponseEntity.ok(updatedWarehouse);
    }
}
