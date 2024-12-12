package com.devops.backend.model;


import com.devops.backend.model.Package.DeliveryPackage;
import jakarta.persistence.*;

@Entity
@Table(name = "deliveryRequest")
public class DeliveryRequest {
    @Transient
    public static final double COST_PER_KG = 11.5;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private User client;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "package_id", nullable = false)
    private DeliveryPackage packageDetails;

    @OneToOne
    @JoinColumn(name = "payment_id", nullable = false)
    private Payment payment;

    private boolean isAssigned=false;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "number", column = @Column(name = "sender_number")),
            @AttributeOverride(name = "street", column = @Column(name = "sender_street")),
            @AttributeOverride(name = "city", column = @Column(name = "sender_city")),
            @AttributeOverride(name = "country", column = @Column(name = "sender_country")),
            @AttributeOverride(name = "ZipCode", column = @Column(name = "sender_zip_code"))
    })
    private Address senderAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "number", column = @Column(name = "receiver_number")),
            @AttributeOverride(name = "street", column = @Column(name = "receiver_street")),
            @AttributeOverride(name = "city", column = @Column(name = "receiver_city")),
            @AttributeOverride(name = "country", column = @Column(name = "receiver_country")),
            @AttributeOverride(name = "ZipCode", column = @Column(name = "receiver_zip_code"))
    })
    private Address receiverAddress;

    private double cost;

    // Getters and Setters

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public User getClient() {
        return client;
    }
    public void setClient(User client) {
        this.client = client;
    }
    public DeliveryPackage getPackageDetails() {
        return packageDetails;
    }
    public void setPackageDetails(DeliveryPackage packageDetails) {
        this.packageDetails = packageDetails;
    }
    public Payment getPayment() {
        return payment;
    }
    public void setPayment(Payment payment) {
        this.payment = payment;
    }
    public double getCost() {
        return cost;
    }
    public void setCost(double cost) {
        this.cost = cost;
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
    public boolean isAssigned() {
        return isAssigned;
    }
    public void setAssigned(boolean assigned) {
        isAssigned = assigned;
    }

}
