package com.devops.backend.service.observer;
import com.devops.backend.model.DeliveryRequest;
import com.devops.backend.model.Tracker;

public interface Observer {
    void update(long deliveryRequestId, Tracker tracker);
}