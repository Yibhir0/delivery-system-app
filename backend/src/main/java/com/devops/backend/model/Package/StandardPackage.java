package com.devops.backend.model.Package;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Qualifier;

@Entity
@DiscriminatorValue("STANDARD")
@Qualifier("STANDARD_PACKAGE")
public class StandardPackage extends DeliveryPackage {
    // Additional attributes and methods specific to StandardPackage
}


