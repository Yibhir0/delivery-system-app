package com.devops.backend.service.strategy;

import com.devops.backend.form.PaymentForm;
import com.devops.backend.model.CustomType.PaymentStatus;
import com.devops.backend.model.Payment;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service("creditCardPaymentStrategy")
public class CreditCardPaymentStrategy implements PaymentStrategy {

    @Override
    public Payment processPayment(PaymentForm paymentForm) {
        Payment payment = new Payment();
        payment.setAmount(paymentForm.getAmount());
        payment.setPaymentType(paymentForm.getPaymentType());
        payment.setPaymentDate(LocalDateTime.now());

        // some processing logic for credit card payment
        PaymentStatus paymentStatus = PaymentStatus.COMPLETED;

        payment.setStatus(paymentStatus);
        return payment;
    }
    
}
