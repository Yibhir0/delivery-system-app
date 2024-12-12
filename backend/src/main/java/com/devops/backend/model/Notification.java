package com.devops.backend.model;

import com.devops.backend.model.Package.DeliveryPackage;
import jakarta.persistence.*;

@Entity
public class Notification{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long deliveryRequestId;


//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
//    private User user;
//
//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "package_id", referencedColumnName = "id")
//    private DeliveryPackage pkg;
    private String message;
    private boolean read;

    // Getters and Setters
    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getMessage(){
        return this.message;
    }
    public void setMessage(String message){
        this.message = message;
    }
    public boolean isRead(){
        return this.read;
    }
    public void setRead(boolean read){
        this.read = read;
    }
    public long getDeliveryRequest(){
        return this.deliveryRequestId;
    }
    public void setDeliveryRequest(long deliveryRequestId){
        this.deliveryRequestId = deliveryRequestId;
    }
//    public User getUser(){
//        return this.user;
//    }
//    public void setUser(User user){
//        this.user = user;
//    }
//    public DeliveryPackage getPackage() {
//        return pkg;
//    }
//    public void setPackage(DeliveryPackage pkg) {
//        this.pkg = pkg;
//    }
}


