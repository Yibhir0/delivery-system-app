package com.devops.backend.service;

import com.devops.backend.form.PackageForm;
import com.devops.backend.model.DeliveryRequest;
import com.devops.backend.model.Package.DeliveryPackage;
import com.devops.backend.repository.DeliveryRequestRepository;
import com.devops.backend.repository.PackageRepository;
import com.devops.backend.service.factory.PackageFactory;
import com.devops.backend.model.CustomType.PackageType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PackageService {
    private final PackageRepository packageRepository;
    private final PackageFactory packageFactory;
    private final DeliveryRequestRepository deliveryRequestRepository;

    public PackageService(PackageRepository packageRepository, PackageFactory packageFactory, DeliveryRequestRepository deliveryRequestRepository) {
        this.packageRepository = packageRepository;
        this.packageFactory = packageFactory;
        this.deliveryRequestRepository = deliveryRequestRepository;
    }

    public DeliveryPackage create(PackageType type) {
        return packageFactory.createPackage(type);
    }

    public List<DeliveryPackage> getAll() {
        return packageRepository.findAll();
    }

    public DeliveryPackage get(Long id) {
        return packageRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Package ID " + id + " not found"));
    }

    public DeliveryPackage save(PackageForm packageForm) {
        DeliveryPackage pkg = create(packageForm.getPackageType());
        pkg.setWeight(packageForm.getWeight());
        pkg.setHeight(packageForm.getHeight());
        pkg.setWidth(packageForm.getWidth());
        pkg.setLength(packageForm.getLength());
        return packageRepository.save(pkg);
    }

//    public DeliveryPackage updateWeight(Long id, double weight) {
//        DeliveryPackage pkg = packageRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Package ID " + id + " not found"));
//        pkg.setWeight(weight);
//        return packageRepository.save(pkg);
//    }
//
//    public DeliveryPackage updateHeight(Long id, double height) {
//        DeliveryPackage pkg = packageRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Package ID " + id + " not found"));
//        pkg.setHeight(height);
//        return packageRepository.save(pkg);
//    }
//
//    public DeliveryPackage updateWidth(Long id, double width) {
//        DeliveryPackage pkg = packageRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Package ID " + id + " not found"));
//        pkg.setWidth(width);
//        return packageRepository.save(pkg);
//    }
//
//    public DeliveryPackage updateLength(Long id, double length) {
//        DeliveryPackage pkg = packageRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Package ID " + id + " not found"));
//        pkg.setLength(length);
//        return packageRepository.save(pkg);
//    }

    public void delete(Long id) {
        packageRepository.deleteById(id);
    }

    public DeliveryRequest getDeliveryRequestByPackageId(Long id) {
        List<DeliveryRequest> deliveryRequests = deliveryRequestRepository.findAll();
        for (DeliveryRequest deliveryRequest : deliveryRequests) {
            if (deliveryRequest.getPackageDetails().getId().equals(id)) {
                return deliveryRequest;
            }
        }
        throw new IllegalArgumentException("No package found for id: " + id);
    }

    public DeliveryPackage updateDimensions(Long packageId, PackageForm packageForm) {
        DeliveryPackage pkg = packageRepository.findById(packageId).orElseThrow(() -> new IllegalArgumentException("Package ID " + packageId + " not found"));
        pkg.setWeight(packageForm.getWeight());
        pkg.setHeight(packageForm.getHeight());
        pkg.setWidth(packageForm.getWidth());
        pkg.setLength(packageForm.getLength());
        return packageRepository.save(pkg);
    }
}