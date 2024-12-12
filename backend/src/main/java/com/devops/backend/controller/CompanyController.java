package com.devops.backend.controller;

import com.devops.backend.model.Address;
import com.devops.backend.model.Company;
import com.devops.backend.model.DeliveryRequest;
import com.devops.backend.service.CompanyService;
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
@RequestMapping(value="/api/company", produces = "application/json")
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @Tag(name = "Company GET Endpoints / Admin GET Endpoints")
    @Operation(summary = "Get the company's information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Company retrieved"),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping(value="/")
    public ResponseEntity<Company> getDefaultCompany() {
        Company defaultCompany = companyService.getDefaultCompany();
        return ResponseEntity.ok(defaultCompany);
    }

    @Tag(name = "Company PUT Endpoints / Admin PUT Endpoints")
    @Operation(summary = "Update the company's name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Company name updated"),
            @ApiResponse(responseCode = "400", description = "Invalid company name", content = @Content),
            @ApiResponse(responseCode = "404", description = "Company not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PutMapping(value="/name",consumes = "application/json")
    public ResponseEntity<Company> updateCompanyName(@RequestBody String name) {
        if(ControllerValidator.isValidString(name)){
            Company updatedCompany = companyService.updateCompanyName(name);
            return ResponseEntity.ok(updatedCompany);
        }
        return ResponseEntity.badRequest().body(null);
    }


    @Tag(name = "Company PUT Endpoints / Admin PUT Endpoints")
    @Operation(summary = "Update the company's address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Company address updated"),
            @ApiResponse(responseCode = "400", description = "Invalid company address", content = @Content),
            @ApiResponse(responseCode = "404", description = "Company not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PutMapping(value="/address",consumes = "application/json")
    public ResponseEntity<Company> updateCompanyAddress(@RequestBody Address address) {
        if(ControllerValidator.isValidAddress(address)) {
            Company updatedCompany = companyService.updateCompanyAddress(address);
            return ResponseEntity.ok(updatedCompany);
        }
        return ResponseEntity.badRequest().body(null);
    }

    @Tag(name = "Company POST Endpoints / Admin POST Endpoints")
    @Operation(summary = "Add a warehouse to the company")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Warehouse added"),
            @ApiResponse(responseCode = "400", description = "Invalid warehouse address", content = @Content),
            @ApiResponse(responseCode = "404", description = "Company not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PostMapping(value="/warehouse",consumes = "application/json")
    public ResponseEntity<Company> addWarehouse(@RequestBody Address address) {
        if(ControllerValidator.isValidAddress(address)) {
            Company updatedCompany = companyService.addWarehouse(address);
            return ResponseEntity.ok(updatedCompany);
        }
        return ResponseEntity.badRequest().body(null);
    }

    @Tag(name = "Company DELETE Endpoints / Admin DELETE Endpoints")
    @Operation(summary = "Remove a warehouse from the company")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Warehouse removed"),
            @ApiResponse(responseCode = "400", description = "Invalid warehouse ID", content = @Content),
            @ApiResponse(responseCode = "404", description = "Company not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @DeleteMapping(value="/warehouse/{warehouseId}")
    public ResponseEntity<Company> removeWarehouse(@PathVariable Long warehouseId) {
        Company updatedCompany = companyService.removeWarehouse(warehouseId);
        return ResponseEntity.ok(updatedCompany);
    }

    @Tag(name = "Company DELETE Endpoints / Admin DELETE Endpoints")
    @Operation(summary = "Remove a driver from the company")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Driver removed"),
            @ApiResponse(responseCode = "400", description = "Invalid driver ID", content = @Content),
            @ApiResponse(responseCode = "404", description = "Company not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @DeleteMapping(value="/driver/{driverId}")
    public ResponseEntity<Company> removeDriver(@PathVariable Long driverId) {
        Company updatedCompany = companyService.removeDriver(driverId);
        return ResponseEntity.ok(updatedCompany);
    }

}