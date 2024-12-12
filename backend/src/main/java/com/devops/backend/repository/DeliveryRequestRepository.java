package com.devops.backend.repository;

import com.devops.backend.model.DeliveryRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryRequestRepository extends JpaRepository<DeliveryRequest, Long> {
    
    // Example: Find all delivery requests for a specific client by client ID
    List<DeliveryRequest> findByClientId(Long client_id);

    DeliveryRequest findByPackageDetailsId(Long packageId);

    List<DeliveryRequest> findByIsAssignedFalse();

    // Example: Find all delivery requests with a specific payment status
//    List<DeliveryRequest> findByPaymentStatus(String status);
}
