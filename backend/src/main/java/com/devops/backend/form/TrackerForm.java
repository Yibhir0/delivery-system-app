package com.devops.backend.form;

import com.devops.backend.model.Address;
import com.devops.backend.model.CustomType.PackageStatus;

public class TrackerForm {
    private PackageStatus status;
    private Address location;

    public PackageStatus getStatus(){
        return this.status;
    }
    public Address getLocation(){
        return this.location;
    }
    public void setStatus(PackageStatus status){
        this.status = status;
    }
    public void setLocation(Address location) {
        this.location = location;
    }
}
