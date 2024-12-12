package com.devops.backend.service;

import com.devops.backend.model.DeliveryRequest;
import com.devops.backend.model.Notification;
import com.devops.backend.repository.DeliveryRequestRepository;
import com.devops.backend.repository.NotificationRepository;
import com.devops.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;
    private final DeliveryRequestRepository deliveryRequestRepository;

    public NotificationService(NotificationRepository notificationRepository, UserRepository userRepository, DeliveryRequestRepository deliveryRequestRepository){
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
        this.deliveryRequestRepository = deliveryRequestRepository;
    }

    public Notification save(Notification notification){
        return notificationRepository.save(notification);
    }

    public Notification get(Long id){
        return notificationRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Notification ID not valid"));
    }

    public List<Notification> getAll(){
        return notificationRepository.findAll();
    }

    public List<Notification> getAllByUserId(Long userId) {
        userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User ID not valid"));
        // Get all delivery requests for the user
        List<DeliveryRequest> deliveryRequests = deliveryRequestRepository.findByClientId(userId);

        // Get all notifications related to the list of delivery requests and filter by unread
        return notificationRepository.findAll().stream()
                .filter(notification -> deliveryRequests.stream().anyMatch(dr -> dr.getId() == (notification.getDeliveryRequest())))
                .collect(Collectors.toList());
    }

    public void delete(Long id){
        notificationRepository.deleteById(id);
    }

    public Notification markRead(Long id, boolean read){
        Optional<Notification> n = notificationRepository.findById(id);
        if (n.isEmpty()) {
            throw new IllegalArgumentException("Notification ID not valid");
        }
        Notification notification = n.get();
        notification.setRead(read);
        return notificationRepository.save(notification);
    }

    public List<Notification> getAllUnread(Long userId) {
        // Get all delivery requests for the user
        List<DeliveryRequest> deliveryRequests = deliveryRequestRepository.findByClientId(userId);

        // Get all notifications related to the list of delivery requests and filter by unread
        return notificationRepository.findAll().stream()
                .filter(notification -> deliveryRequests.stream().anyMatch(dr -> dr.getId() == (notification.getDeliveryRequest())) && !notification.isRead())
                .collect(Collectors.toList());
    }

    public Long getNumberOfUnreadByUserId(Long userId) {
        // verify if the user exists
        userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User ID not valid"));
        return (long) getAllUnread(userId).size();
    }
}