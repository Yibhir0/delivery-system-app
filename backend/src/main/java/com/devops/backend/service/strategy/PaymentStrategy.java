package com.devops.backend.service.strategy;
import com.devops.backend.form.PaymentForm;
import com.devops.backend.model.Payment;

public interface PaymentStrategy {
    
    Payment processPayment(PaymentForm payment);
}
