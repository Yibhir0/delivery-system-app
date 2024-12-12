package com.devops.backend.model;

import com.devops.backend.model.Package.DeliveryPackage;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Embedded
    private Address address;

    @OneToMany(fetch = FetchType.EAGER)
    private List<DeliveryRequest> packages;

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address a) {
        this.address = a;
    }
    public List<DeliveryRequest> getPackages() {
        return packages;
    }
    public void setPackages(List<DeliveryRequest> p) {
        this.packages = p;
    }
}
