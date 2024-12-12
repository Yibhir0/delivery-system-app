package com.devops.backend.service;

import com.devops.backend.form.UserForm;
import com.devops.backend.model.Address;
import com.devops.backend.model.DeliveryRequest;
import com.devops.backend.model.Driver;
import com.devops.backend.repository.DeliveryRequestRepository;
import com.devops.backend.repository.DriverRepository;
import com.devops.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DriverService{

    private final DriverRepository driverRepository;
    private final UserRepository userRepository;
    private final DeliveryRequestRepository deliveryRequestRepository;

    @Autowired
    public DriverService(DriverRepository driverRepository, UserRepository userRepository, DeliveryRequestRepository deliveryRequestRepository) {
        this.driverRepository = driverRepository;
        this.userRepository = userRepository;
        this.deliveryRequestRepository = deliveryRequestRepository;
    }

    // Get a driver by its ID
    public Driver getDriverById(Long id) {
        Optional<Driver> driver = driverRepository.findById(id);
        if (driver.isPresent()) {
            return driver.get();
        } else {
            throw new IllegalArgumentException("Driver with id " + id + " not found");
        }
    }

    // Get all drivers
    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    // Save a driver
    public Driver saveDriver(UserForm userForm) {
        // check if email is unique
        if (userRepository.findByEmail(userForm.getEmail())!=null) {
            throw new IllegalArgumentException("Email already exists");
        }
        Driver driver = new Driver();
        driver.setFirst_name(userForm.getFirst_name());
        driver.setLast_name(userForm.getLast_name());
        driver.setEmail(userForm.getEmail());
        driver.setPassword(userForm.getPassword());
        driver.setUserType(userForm.getUserType());
        driver.setAddress(getDefaultAddress());
        return userRepository.save(driver);
    }

    private Address getDefaultAddress() {
        Address address = new Address();
        address.setNumber(1455);
        address.setStreet("Boulevard de Maisonneuve");
        address.setCity("Montreal");
        address.setCountry("Canada");
        address.setZipCode("H3G 1M8");
        return address;
    }

    // add a package to a driver
    public Driver addPackage(Long id, Long deliveryId) {
        Optional<Driver> driver = driverRepository.findById(id);
        Optional<DeliveryRequest> deliveryRequest = deliveryRequestRepository.findById(deliveryId);
        if (driver.isPresent() && deliveryRequest.isPresent()) {
            driver.get().getPackages().add(deliveryRequest.get());
            deliveryRequest.get().setAssigned(true);
            deliveryRequestRepository.save(deliveryRequest.get());
            return driverRepository.save(driver.get());
        } else if (driver.isEmpty()) {
            throw new IllegalArgumentException("Driver with id " + id + " not found");
        } else {
            throw new IllegalArgumentException("Package with delivery id " + deliveryId + " not found");
        }
    }

    // Remove a package from a driver
    public Driver removePackage(Long id, Long deliveryId) {
        Optional<Driver> driver = driverRepository.findById(id);
        Optional<DeliveryRequest> deliveryRequest = deliveryRequestRepository.findById(deliveryId);
        if (driver.isPresent() && deliveryRequest.isPresent()) {
            driver.get().getPackages().remove(deliveryRequest.get());
            deliveryRequest.get().setAssigned(false);
            deliveryRequestRepository.save(deliveryRequest.get());
            return driverRepository.save(driver.get());
        } else if (driver.isEmpty()) {
            throw new IllegalArgumentException("Driver with id " + id + " not found");
        } else {
            throw new IllegalArgumentException("Package with delivery id " + deliveryRequest + " not found");
        }
    }

    public List<DeliveryRequest> getPackages(Long driverId) {
        Optional<Driver> driver = driverRepository.findById(driverId);
        if (driver.isPresent()) {
            List<DeliveryRequest> packages = driver.get().getPackages();
            if (packages == null) {
                packages = List.of();
            }
            return packages;
        } else {
            throw new IllegalArgumentException("Driver with id " + driverId + " not found");
        }
    }
}