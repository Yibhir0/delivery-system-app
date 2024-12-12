package com.devops.backend.form;

import com.devops.backend.model.CustomType.PaymentType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class PaymentForm {
    @NotNull(message = "Delivery Request ID is required")
    private Long deliveryRequestId;
    @NotNull
    private PaymentType paymentType;
    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be greater than 0")
    private double amount;

    public Long getDeliveryRequestId() {
        return deliveryRequestId;
    }
    public void setDeliveryRequestId(Long deliveryRequestId) {
        this.deliveryRequestId = deliveryRequestId;
    }
    public PaymentType getPaymentType() {
        return paymentType;
    }
    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
}
