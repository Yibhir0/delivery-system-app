package com.devops.backend.service;

import com.devops.backend.form.PaymentForm;
import com.devops.backend.model.CustomType.PaymentType;
import com.devops.backend.model.DeliveryRequest;
import com.devops.backend.model.Payment;
import com.devops.backend.repository.DeliveryRequestRepository;
import com.devops.backend.repository.PaymentRepository;
import com.devops.backend.service.strategy.PaymentStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final DeliveryRequestRepository deliveryRequestRepository;

    public PaymentService(PaymentRepository paymentRepository, DeliveryRequestRepository deliveryRequestRepository) {
        this.paymentRepository = paymentRepository;
        this.deliveryRequestRepository = deliveryRequestRepository;
    }

    @Autowired
    private ApplicationContext context;

    public Payment processPayment(PaymentForm paymentForm) {
        // validate the delivery request ID
        DeliveryRequest dr = deliveryRequestRepository.findById(paymentForm.getDeliveryRequestId())
                .orElseThrow(() -> new IllegalArgumentException("Delivery Request ID " + paymentForm.getDeliveryRequestId() + " not found"));
        Payment placeholderPayment = dr.getPayment();
        dr.setPayment(null);
        paymentRepository.delete(placeholderPayment);
        // Select the appropriate strategy based on payment type
        PaymentStrategy strategy = switch (paymentForm.getPaymentType()) {
            case PaymentType.CREDIT_CARD -> (PaymentStrategy) context.getBean("creditCardPaymentStrategy");
            case PaymentType.PAYPAL -> (PaymentStrategy) context.getBean("paypalPaymentStrategy");
        };

        // Process payment using the selected strategy
        Payment processedPayment = strategy.processPayment(paymentForm);
        processedPayment = paymentRepository.save(processedPayment);
        dr.setPayment(processedPayment);
        deliveryRequestRepository.save(dr);
        // Save and return processed payment
        return processedPayment;
    }

    // get all payments
    public List<Payment> getAll(){
        return paymentRepository.findAll();
    }

    // get payment by id
    public Payment getPaymentById(Long id){
        Optional<Payment> payment = paymentRepository.findById(id);
        return payment.orElseThrow(() -> new IllegalArgumentException("Payment ID " + id + " not found"));
    }


    // TODO: what cn be updated in a payment
    public Payment updatePayment(Long id, PaymentForm payment) {
        Optional<Payment> optionalPayment = paymentRepository.findById(id);
        if (optionalPayment.isPresent()) {
            Payment existingPayment = optionalPayment.get();
            existingPayment.setAmount(payment.getAmount());
            existingPayment.setPaymentType(payment.getPaymentType());
            //existingPayment.setStatus(payment.getStatus());
            return paymentRepository.save(existingPayment);
        } else {
            throw new IllegalArgumentException("Payment with ID " + id + " not found");
        }
    }
}