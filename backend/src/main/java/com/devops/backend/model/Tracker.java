package com.devops.backend.model;

import com.devops.backend.model.CustomType.PackageStatus;
import com.devops.backend.service.observer.Observer;
import com.devops.backend.service.observer.Subject;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Tracker implements Subject {

    @Transient
    private List<Observer> observers = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private PackageStatus status;

    @Embedded
    private Address location;

    // Getters and Setters
    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public PackageStatus getStatus(){
        return this.status;
    }
    public Address getLocation(){
        return this.location;
    }

    public void setStatus(PackageStatus status){
        this.status = status;
    }
    public void setLocation(Address location){
        this.location = location;
    }

    public void updateTracker(PackageStatus status, Address location, long deliveryRequestId) {
        setStatus(status);
        setLocation(location);
        notifyObservers(deliveryRequestId);
    }
    // Observer pattern methods
    public void addObserver(Observer observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(long deliveryRequestId) {
        for (Observer observer : observers) {
            observer.update(deliveryRequestId, this);
        }
    }
}


