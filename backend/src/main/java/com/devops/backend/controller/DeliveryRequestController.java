package com.devops.backend.controller;

import com.devops.backend.form.DeliveryRequestForm;
import com.devops.backend.model.Address;
import com.devops.backend.model.DeliveryRequest;
import com.devops.backend.service.DeliveryRequestService;
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
@RequestMapping(value="/api/deliveryRequest/", produces = "application/json")
public class DeliveryRequestController {
    private final DeliveryRequestService deliveryRequestService;

    @Autowired
    public DeliveryRequestController(DeliveryRequestService deliveryRequestService) {
        this.deliveryRequestService = deliveryRequestService;
    }

    @Tag(name = "Company GET Endpoints / Admin GET Endpoints")
    @Operation(summary = "Get all delivery requests")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delivery requests retrieved"),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping(value="/")
    public ResponseEntity<List<DeliveryRequest>> getAllDeliveryRequests() {
        List<DeliveryRequest> deliveryRequests = deliveryRequestService.getAllDeliveryRequests();
        return new ResponseEntity<>(deliveryRequests, HttpStatus.OK);
    }

    @Tags({
            @Tag(name = "Company GET Endpoints / Admin GET Endpoints"),
            @Tag(name = "Driver GET Endpoints")
    })
    @Operation(summary = "Get all unassigned packages for a driver")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "packages retrieved"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    @GetMapping(value="/unassigned")
    public ResponseEntity<List<DeliveryRequest>> getUnassignedPackages() {
        List<DeliveryRequest> deliveryRequests = deliveryRequestService.getUnassignedPackages();
        return new ResponseEntity<>(deliveryRequests, HttpStatus.OK);
    }

    @Tags({
            @Tag(name = "Company GET Endpoints / Admin GET Endpoints"),
            @Tag(name = "User GET Endpoints"),
            @Tag(name = "Driver GET Endpoints"),
    })
    @Operation(summary = "Get a delivery request by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delivery request retrieved"),
            @ApiResponse(responseCode = "404", description = "Delivery request ID not valid", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping(value="/{deliveryRequestId}")
    public ResponseEntity<DeliveryRequest> getDeliveryRequestById(@PathVariable Long deliveryRequestId) {
        DeliveryRequest deliveryRequest = deliveryRequestService.getDeliveryRequestById(deliveryRequestId);
        return ResponseEntity.ok(deliveryRequest);
    }

    @Tag(name = "User GET Endpoints")
    @Operation(summary = "Get delivery requests by user ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of delivery requests retrieved"),
            @ApiResponse(responseCode = "404", description = "User ID not valid", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping(value="/client/{userId}")
    public ResponseEntity<List<DeliveryRequest>> getDeliveryRequestsByUserId(@PathVariable Long userId) {
        List<DeliveryRequest> deliveryRequests = deliveryRequestService.getDeliveryRequestsByUserId(userId);
        return ResponseEntity.ok(deliveryRequests);
    }

    @Tag(name = "User POST Endpoints")
    @Operation(summary = "Create a new delivery request")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Delivery request created"),
            @ApiResponse(responseCode = "400", description = "Invalid delivery request", content = @Content),
            @ApiResponse(responseCode = "404", description = "User ID not valid", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PostMapping(value="/" , consumes = "application/json")
    public ResponseEntity<DeliveryRequest> save(@RequestBody DeliveryRequestForm deliveryRequestForm) {
        //TODO: Add validation for deliveryRequestForm
        DeliveryRequest savedDeliveryRequest = deliveryRequestService.save(deliveryRequestForm);
        return new ResponseEntity<>(savedDeliveryRequest, HttpStatus.CREATED);
    }

    @Tag(name = "User PUT Endpoints")
    @Operation(summary = "Update sender address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sender address updated"),
            @ApiResponse(responseCode = "400", description = "Invalid address", content = @Content),
            @ApiResponse(responseCode = "404", description = "Delivery request not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PutMapping(value="/{deliveryRequestId}/senderAddress", consumes = "application/json")
    public ResponseEntity<DeliveryRequest> updateSenderAddress(@PathVariable Long deliveryRequestId, @RequestBody Address senderAddress) {
        if(ControllerValidator.isValidAddress(senderAddress)) {
            DeliveryRequest updatedDeliveryRequest = deliveryRequestService.updateSenderAddress(deliveryRequestId, senderAddress);
            return ResponseEntity.ok(updatedDeliveryRequest);
        }
        return ResponseEntity.badRequest().build();
    }

    @Tag(name = "User PUT Endpoints")
    @Operation(summary = "Update receiver address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Receiver address updated"),
            @ApiResponse(responseCode = "400", description = "Invalid address", content = @Content),
            @ApiResponse(responseCode = "404", description = "Delivery request not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PutMapping(value="/{deliveryRequestId}/receiverAddress", consumes = "application/json")
    public ResponseEntity<DeliveryRequest> updateReceiverAddress(@PathVariable Long deliveryRequestId, @RequestBody Address receiverAddress) {
        if(ControllerValidator.isValidAddress(receiverAddress)) {
            DeliveryRequest updatedDeliveryRequest = deliveryRequestService.updateReceiverAddress(deliveryRequestId, receiverAddress);
            return ResponseEntity.ok(updatedDeliveryRequest);
        }
        return ResponseEntity.badRequest().build();
    }

    @Tags({
            @Tag(name = "Company DELETE Endpoints / Admin DELETE Endpoints"),
            @Tag(name = "User DELETE Endpoints"),
    })
    @Operation(summary = "Delete a delivery request")
    @DeleteMapping(value="/{deliveryRequestId}")
    public ResponseEntity<Void> deleteDeliveryRequest(@PathVariable Long deliveryRequestId) {
        try {
            deliveryRequestService.delete(deliveryRequestId);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
