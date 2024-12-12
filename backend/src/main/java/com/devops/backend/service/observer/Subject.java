package com.devops.backend.service.observer;

import com.devops.backend.model.DeliveryRequest;

public interface Subject {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(long deliveryRequestId);
}