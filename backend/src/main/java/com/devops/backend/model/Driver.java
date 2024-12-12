package com.devops.backend.model;

import com.devops.backend.model.CustomType.UserType;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Driver extends User {

    // attribute list of packages
    @OneToMany(fetch = FetchType.EAGER)
    private List<DeliveryRequest> packages;

    public Driver() {
        this.setUserType(UserType.DRIVER);
    }

    public List<DeliveryRequest> getPackages() {
        return packages;
    }
    public void setPackages(List<DeliveryRequest> packages) {
        this.packages = packages;
    }
}
