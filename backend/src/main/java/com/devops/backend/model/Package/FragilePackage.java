package com.devops.backend.model.Package;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Qualifier;

@Entity
@DiscriminatorValue("FRAGILE")
@Qualifier("FRAGILE_PACKAGE")
public class FragilePackage extends DeliveryPackage {
    // Additional attributes and methods specific to FragilePackage
}
