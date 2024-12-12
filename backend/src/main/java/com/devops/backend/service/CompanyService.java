package com.devops.backend.service;

import com.devops.backend.model.*;
import com.devops.backend.repository.CompanyRepository;
import com.devops.backend.repository.UserRepository;
import com.devops.backend.repository.WarehouseRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;
    private final WarehouseRepository warehouseRepository;

    public CompanyService(CompanyRepository companyRepository, UserRepository userRepository, WarehouseRepository warehouseRepository) {
        this.companyRepository = companyRepository;
        this.userRepository = userRepository;
        this.warehouseRepository = warehouseRepository;
    }

    @PostConstruct
    public void initialize() {
        long companyCount = companyRepository.count();
        if (companyCount == 0) {
            createDefaultCompany();
        } else if (companyCount > 1) {
            // throw an exception if there are more than one company
            throw new IllegalArgumentException("Multiple companies found");
        }
    }

    private void createDefaultCompany() {
        Company defaultCompany = new Company();
        defaultCompany.setName("Habibi Deliveries");
        Address address = new Address();
        address.setNumber(1455);
        address.setStreet("Boulevard de Maisonneuve");
        address.setCity("Montreal");
        address.setCountry("Canada");
        address.setZipCode("H3G 1M8");
        defaultCompany.setAddress(address);
        // add a default warehouse
        defaultCompany.setWarehouses(List.of(this.createDefaultWarehouse(address)));
        // add a default driver
        defaultCompany.setDrivers(List.of(this.createDefaultDriver(address)));
        companyRepository.save(defaultCompany);
    }

    private Driver createDefaultDriver(Address address) {
        Driver driver = new Driver();
        driver.setFirst_name("Ali");
        driver.setLast_name("Habibi");
        driver.setEmail("driver@example.com");
        driver.setPassword("driver123");
        driver.setAddress(address);
        return userRepository.save(driver);
    }

    private Warehouse createDefaultWarehouse(Address address) {
        Warehouse warehouse = new Warehouse();
        warehouse.setAddress(address);
        return warehouseRepository.save(warehouse);
    }

    public Company getDefaultCompany() {
        Optional<Company> defaultCompany = companyRepository.findById(1L);
        return defaultCompany.orElseThrow(() -> new IllegalArgumentException("Company not found"));
    }

    public Company updateCompanyName(String name) {
        Company defaultCompany = getDefaultCompany();
        defaultCompany.setName(name);
        return companyRepository.save(defaultCompany);
    }

    public Company updateCompanyAddress(Address address) {
        Company defaultCompany = getDefaultCompany();
        defaultCompany.setAddress(address);
        return companyRepository.save(defaultCompany);
    }

    // add a warehouse to the company
    public Company addWarehouse(Address address) {
        Warehouse warehouse = new Warehouse();
        warehouse.setAddress(address);
        Company defaultCompany = getDefaultCompany();
        List<Warehouse> warehouses = defaultCompany.getWarehouses();
        Warehouse newWarehouse = warehouseRepository.save(warehouse);
        warehouses.add(newWarehouse);
        defaultCompany.setWarehouses(warehouses);
        return companyRepository.save(defaultCompany);
    }

    // remove a warehouse from the company
    public Company removeWarehouse(Long warehouseId) {
        Company defaultCompany = getDefaultCompany();
        List<Warehouse> warehouses = defaultCompany.getWarehouses();
        boolean warehouseExists = warehouses.removeIf(warehouse -> warehouse.getId().equals(warehouseId));
        if (!warehouseExists) {
            throw new IllegalArgumentException("Invalid warehouse ID");
        }
        defaultCompany.setWarehouses(warehouses);
        return companyRepository.save(defaultCompany);
    }

    // remove a driver from a company
    public Company removeDriver(Long driverId) {
        Company defaultCompany = getDefaultCompany();
        List<Driver> drivers = defaultCompany.getDrivers();
        boolean driverExists = drivers.removeIf(driver -> driver.getId().equals(driverId));
        if (!driverExists) {
            throw new IllegalArgumentException("Invalid driver ID");
        }
        defaultCompany.setDrivers(drivers);
        return companyRepository.save(defaultCompany);
    }

}