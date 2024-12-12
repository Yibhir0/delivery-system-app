package com.devops.backend.form;

public class PaypalPaymentForm extends PaymentForm{
    private String email;
    private String transactionId;

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getTransactionId() {
        return transactionId;
    }
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}
