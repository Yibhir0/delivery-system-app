package com.devops.backend.model.Package;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Qualifier;

@Entity
@DiscriminatorValue("LITHIUM")
@Qualifier("LITHIUM_PACKAGE")
public class LithiumBatteryPackage extends DeliveryPackage {
    // Additional attributes and methods specific to LithiumBatteryPackage
}