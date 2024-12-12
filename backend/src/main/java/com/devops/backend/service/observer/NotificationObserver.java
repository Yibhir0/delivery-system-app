package com.devops.backend.service.observer;

import com.devops.backend.model.DeliveryRequest;
import com.devops.backend.model.Notification;
import com.devops.backend.model.Tracker;
import com.devops.backend.repository.DeliveryRequestRepository;
import com.devops.backend.repository.NotificationRepository;
import org.springframework.stereotype.Component;


@Component
public class NotificationObserver implements Observer {

    private final NotificationRepository notificationRepository;

    public NotificationObserver(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public void update(long deliveryRequestId, Tracker tracker) {
        Notification notification = new Notification();
        notification.setDeliveryRequest(deliveryRequestId);
        notification.setMessage("Tracker with ID " + tracker.getId() + " status changed to: " + tracker.getStatus());
        notification.setRead(false);
        notificationRepository.save(notification);
    }

}