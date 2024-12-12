package com.devops.backend.controller;

import com.devops.backend.form.UserForm;
import com.devops.backend.model.CustomType.UserType;
import com.devops.backend.model.DeliveryRequest;
import com.devops.backend.model.Driver;
import com.devops.backend.service.DriverService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api/driver/", produces = "application/json")
public class DriverController {

    private final DriverService driverService;

    @Autowired
    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @Tag(name="Company GET Endpoints / Admin GET Endpoints")
    @Operation(summary = "Get all drivers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Drivers retrieved"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    @GetMapping(value="/")
    public ResponseEntity<List<Driver>>getAllDrivers() {
        List<Driver> drivers= driverService.getAllDrivers();
        return  ResponseEntity.ok(drivers);
    }

    @Tags({
            @Tag(name = "Company GET Endpoints / Admin GET Endpoints"),
            @Tag(name = "Driver GET Endpoints")
    })
    @Operation(summary = "get assigned packages for a driver")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Packages retrieved"),
            @ApiResponse(responseCode = "404", description = "Driver ID not valid", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    @GetMapping(value="/{driverId}/packages")
    public ResponseEntity<List<DeliveryRequest>> addPackage(@PathVariable Long driverId) {
        List<DeliveryRequest> packages = driverService.getPackages(driverId);
        return ResponseEntity.ok(packages);
    }

    @Tag(name="Company GET Endpoints / Admin GET Endpoints")
    @Operation(summary = "Get a driver by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Driver retrieved"),
            @ApiResponse(responseCode = "404", description = "Driver ID not valid", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    @GetMapping(value="/{driverId}")
    public ResponseEntity<Driver> getDriverById(@PathVariable Long driverId) {
        Driver driver = driverService.getDriverById(driverId);
        return ResponseEntity.ok(driver);
    }

    @Tag(name = "Driver POST Endpoints")
    @Operation(summary = "Sign up as a driver")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Driver created"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    @PostMapping(value="/signup",consumes = "application/json")
    public ResponseEntity<Driver> createDriver(@RequestBody UserForm userForm) {
        if (!userForm.getUserType().equals(UserType.DRIVER)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        // TODO: Add validation for userForm
        Driver savedDriver = driverService.saveDriver(userForm);
        return new ResponseEntity<>(savedDriver, HttpStatus.CREATED);
    }

    @Tags({
            @Tag(name = "Company PUT Endpoints / Admin PUT Endpoints"),
            @Tag(name = "Driver PUT Endpoints")
    })
    @Operation(summary = "Add a package to a driver")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Package added to driver"),
            @ApiResponse(responseCode = "404", description = "Driver or Package ID not valid", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    @PutMapping(value="/{driverId}/{deliveryId}")
    public ResponseEntity<Driver> addPackage(@PathVariable Long driverId, @PathVariable Long deliveryId) {
        Driver updatedDriver = driverService.addPackage(driverId, deliveryId);
        return ResponseEntity.ok(updatedDriver);
    }

    @Tags({
            @Tag(name = "Company DELETE Endpoints / Admin DELETE Endpoints"),
            @Tag(name = "Driver DELETE Endpoints")
    })
    @Operation(summary = "Remove a package from a driver")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Package removed from driver"),
            @ApiResponse(responseCode = "404", description = "Driver or Package ID not valid", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    @DeleteMapping(value="/{driverId}/{deliveryId}")
    public ResponseEntity<Driver> removePackage(@PathVariable Long driverId, @PathVariable Long deliveryId) {
        Driver updatedDriver = driverService.removePackage(driverId, deliveryId);
        return ResponseEntity.ok(updatedDriver);
    }
}