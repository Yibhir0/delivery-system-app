package com.devops.backend.controller;

import com.devops.backend.form.PackageForm;
import com.devops.backend.model.DeliveryRequest;
import com.devops.backend.model.Package.DeliveryPackage;
import com.devops.backend.service.PackageService;
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
@RequestMapping(value="/api/package", produces = "application/json")
public class PackageController {
    private final PackageService packageService;

    @Autowired
    public PackageController(PackageService packageService){
        this.packageService = packageService;
    }

    @Tag(name="Company GET Endpoints / Admin GET Endpoints")
    @Operation(summary = "Get all packages")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Packages retrieved"),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping(value="/")
    public ResponseEntity<List<DeliveryPackage>> getAll(){
        List<DeliveryPackage> pkgs = packageService.getAll();
        return ResponseEntity.ok(pkgs);
    }

    @Tags({
            @Tag(name = "Company GET Endpoints / Admin GET Endpoints"),
            @Tag(name = "User GET Endpoints"),
    })
    @Operation(summary = "Get a package by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Package retrieved"),
            @ApiResponse(responseCode = "404", description = "Package ID not valid", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping(value="/{packageId}")
    public ResponseEntity<DeliveryPackage> get(@PathVariable Long packageId){
        DeliveryPackage pkg = packageService.get(packageId);
        return ResponseEntity.ok(pkg);
    }

    @Tags({
            @Tag(name = "Company GET Endpoints / Admin GET Endpoints"),
            @Tag(name = "User GET Endpoints"),
    })
    @Operation(summary = "Get deliveryRequest by package ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "DeliveryRequest retrieved"),
            @ApiResponse(responseCode = "404", description = "Package ID not valid", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping(value="/{packageId}/deliveryRequest")
    public ResponseEntity<DeliveryRequest> getDeliveryRequestByPackageId(@PathVariable Long packageId){
        DeliveryRequest deliveryRequest = packageService.getDeliveryRequestByPackageId(packageId);
        return ResponseEntity.ok(deliveryRequest);
    }

    @Tag(name = "User PUT Endpoints")
    @Operation(summary = "Update package dimensions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Package dimensions updated"),
            @ApiResponse(responseCode = "404", description = "Package ID not valid", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PutMapping(value="/{packageId}",consumes = "application/json")
    public ResponseEntity<DeliveryPackage> updateWeight(@PathVariable Long packageId, @RequestBody PackageForm packageForm) {
        DeliveryPackage updatedPackage = packageService.updateDimensions(packageId, packageForm);
        return ResponseEntity.ok(updatedPackage);
    }

//    @Tag(name = "User PUT Endpoints")
//    @Operation(summary = "Update package width")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Package width updated"),
//            @ApiResponse(responseCode = "400", description = "Invalid width", content = @Content),
//            @ApiResponse(responseCode = "404", description = "Package ID not valid", content = @Content),
//            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
//    })
//    @PutMapping(value="/{packageId}/width",consumes = "application/json")
//    public ResponseEntity<DeliveryPackage> updateWidth(@PathVariable Long packageId, @RequestBody double width) {
//        if(ControllerValidator.isValidDouble(width)) {
//            DeliveryPackage updatedPackage = packageService.updateWidth(packageId, width);
//            return ResponseEntity.ok(updatedPackage);
//        }
//        return ResponseEntity.badRequest().build();
//    }
//
//    @Tag(name = "User PUT Endpoints")
//    @Operation(summary = "Update package height")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Package height updated"),
//            @ApiResponse(responseCode = "400", description = "Invalid height", content = @Content),
//            @ApiResponse(responseCode = "404", description = "Package ID not valid", content = @Content),
//            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
//    })
//    @PutMapping(value="/{packageId}/height",consumes = "application/json")
//    public ResponseEntity<DeliveryPackage> updateHeight(@PathVariable Long packageId, @RequestBody double height) {
//        if (ControllerValidator.isValidDouble(height)) {
//            DeliveryPackage updatedPackage = packageService.updateHeight(packageId, height);
//            return ResponseEntity.ok(updatedPackage);
//        }
//        return ResponseEntity.badRequest().build();
//    }
//
//    @Tag(name = "User PUT Endpoints")
//    @Operation(summary = "Update package length")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Package length updated"),
//            @ApiResponse(responseCode = "400", description = "Invalid length", content = @Content),
//            @ApiResponse(responseCode = "404", description = "Package ID not valid", content = @Content),
//            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
//    })
//    @PutMapping(value="/{packageId}/length",consumes = "application/json")
//    public ResponseEntity<DeliveryPackage> updateLength(@PathVariable Long packageId, @RequestBody double length) {
//        if(ControllerValidator.isValidDouble(length)) {
//            DeliveryPackage updatedPackage = packageService.updateLength(packageId, length);
//            return ResponseEntity.ok(updatedPackage);
//        }
//        return ResponseEntity.badRequest().build();
//    }
}