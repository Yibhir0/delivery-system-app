package com.devops.backend.repository;

import com.devops.backend.model.Package.DeliveryPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackageRepository extends JpaRepository<DeliveryPackage, Long> {
    // add custom queries here
}