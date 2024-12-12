package com.devops.backend.service.strategy;

import com.devops.backend.form.PaymentForm;
import com.devops.backend.model.CustomType.PaymentStatus;
import com.devops.backend.model.Payment;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service("paypalPaymentStrategy")
public class PaypalPaymentStrategy implements PaymentStrategy {

    @Override
    public Payment processPayment(PaymentForm paymentForm) {
        Payment payment = new Payment();
        payment.setAmount(paymentForm.getAmount());
        payment.setPaymentDate(LocalDateTime.now());

        // some payment processing logic for paypal
        PaymentStatus status = PaymentStatus.COMPLETED;

        payment.setStatus(status);
        return payment;
    }

    
    
}
