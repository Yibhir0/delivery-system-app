package com.devops.backend.controller;

import com.devops.backend.form.CreditCardPaymentForm;
import com.devops.backend.form.PaymentForm;
import com.devops.backend.form.PaypalPaymentForm;
import com.devops.backend.model.Payment;
import com.devops.backend.service.PaymentService;
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
@RequestMapping(value="/api/payment", produces = "application/json")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Tag(name = "Company GET Endpoints / Admin GET Endpoints")
    @Operation(summary = "Get all payments")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Payments retrieved"),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping(value="/")
    public ResponseEntity<List<Payment>> getAllPayments() {
        List<Payment> payments = paymentService.getAll();
        return  ResponseEntity.ok(payments);
    }

    @Tags({
            @Tag(name = "Company GET Endpoints / Admin GET Endpoints"),
            @Tag(name = "User GET Endpoints"),
    })
    @Operation(summary = "Get a payment by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Payment retrieved"),
            @ApiResponse(responseCode = "404", description = "Payment ID not valid", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping(value="/{paymentId}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long paymentId) {
        Payment payment = paymentService.getPaymentById(paymentId);
        return ResponseEntity.ok(payment);
    }

    @Tag(name = "User POST Endpoints")
    @Operation(summary = "Submit a credit card payment")
    @PostMapping(value="/creditcard" , consumes = "application/json")
    public ResponseEntity<Payment> useCreditCard(@RequestBody CreditCardPaymentForm payment) {
        // TODO: Add validation for credit card payment
        Payment savedPayment = paymentService.processPayment(payment);
        return ResponseEntity.ok(savedPayment);
    }

    @Tag(name = "User POST Endpoints")
    @Operation(summary = "Submit a paypal payment")
    @PostMapping(value="/paypal" , consumes = "application/json")
    public ResponseEntity<Payment> usePayPal(@RequestBody PaypalPaymentForm payment) {
        // TODO: Add validation for paypal payment
        Payment savedPayment = paymentService.processPayment(payment);
        return ResponseEntity.ok(savedPayment);
    }

    @Tag(name = "Company PUT Endpoints / Admin PUT Endpoints")
    @Operation(summary = "update a payment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Payment updated"),
            @ApiResponse(responseCode = "404", description = "Payment ID not valid", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PutMapping(value="/{paymentId}",consumes = "application/json")
    public ResponseEntity<Payment> updatePayment(@PathVariable Long paymentId, @RequestBody PaymentForm payment) {
        Payment updatedPayment = paymentService.updatePayment(paymentId, payment);
        return ResponseEntity.ok(updatedPayment);
    }

}
