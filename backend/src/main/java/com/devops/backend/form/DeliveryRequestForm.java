package com.devops.backend.form;

import com.devops.backend.model.Address;
import com.devops.backend.model.DeliveryRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class DeliveryRequestForm {
    @NotNull(message = "Client ID is required")
    private Long clientId;
    @NotNull(message = "Package Details is required")
    private PackageForm packageDetails;
    @NotNull(message = "Sender Address is required")
    @Valid
    private Address senderAddress;
    @NotNull(message = "Receiver Address is required")
    @Valid
    private Address receiverAddress;

    public Long getClientId() {
        return clientId;
    }
    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
    public PackageForm getPackageDetails() {
        return packageDetails;
    }
    public void setPackageDetails(PackageForm packageDetails) {
        this.packageDetails = packageDetails;
    }
    public Address getSenderAddress() {
        return senderAddress;
    }
    public void setSenderAddress(Address senderAddress) {
        this.senderAddress = senderAddress;
    }
    public Address getReceiverAddress() {
        return receiverAddress;
    }
    public void setReceiverAddress(Address receiverAddress) {
        this.receiverAddress = receiverAddress;
    }
    public double calculateCost() {return packageDetails.getWeight() * DeliveryRequest.COST_PER_KG;}
}
