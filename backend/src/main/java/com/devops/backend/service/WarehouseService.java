package com.devops.backend.service;

import com.devops.backend.model.Address;
import com.devops.backend.model.DeliveryRequest;
import com.devops.backend.model.Package.DeliveryPackage;
import com.devops.backend.model.Warehouse;
import com.devops.backend.repository.DeliveryRequestRepository;
import com.devops.backend.repository.PackageRepository;
import com.devops.backend.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;
    private final PackageRepository deliveryPackageRepository;
    private final DeliveryRequestRepository deliveryRequestRepository;

    @Autowired
    public WarehouseService(WarehouseRepository warehouseRepository, PackageRepository deliveryPackageRepository, DeliveryRequestRepository deliveryRequestRepository) {
        this.warehouseRepository = warehouseRepository;
        this.deliveryPackageRepository = deliveryPackageRepository;
        this.deliveryRequestRepository = deliveryRequestRepository;
    }

    // Get a warehouse by its ID
    public Warehouse getWarehouseById(Long id) {
        return warehouseRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Warehouse with id " + id + " not found"));
    }

    // Get all warehouses
    public List<Warehouse> getAllWarehouses() {
        return warehouseRepository.findAll();
    }

    public Warehouse updateWarehouseAddress(Long id, Address address) {
        Warehouse warehouse = warehouseRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Warehouse with id " + id + " not found"));
        warehouse.setAddress(address);
        return warehouseRepository.save(warehouse);
    }

    public Warehouse addPackage(Long id, Long deliveryId) {
        Warehouse warehouse = warehouseRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Warehouse with id " + id + " not found"));
        DeliveryRequest deliveryRequest = deliveryRequestRepository.findById(deliveryId).orElseThrow(() -> new IllegalArgumentException("Package with delivery id " + deliveryId + " not found"));
        warehouse.getPackages().add(deliveryRequest);
        return warehouseRepository.save(warehouse);
    }

    public Warehouse removePackage(Long id, Long deliveryId) {
        Warehouse warehouse = warehouseRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Warehouse with id " + id + " not found"));
        DeliveryRequest deliveryRequest = deliveryRequestRepository.findById(deliveryId).orElseThrow(() -> new IllegalArgumentException("Package with delivery id " + deliveryId + " not found"));
        warehouse.getPackages().remove(deliveryRequest);
        return warehouseRepository.save(warehouse);
    }
}