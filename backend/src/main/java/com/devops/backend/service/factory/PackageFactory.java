package com.devops.backend.service.factory;

import com.devops.backend.model.CustomType.PackageType;
import com.devops.backend.model.Package.DeliveryPackage;
import com.devops.backend.model.Package.FragilePackage;
import com.devops.backend.model.Package.LithiumBatteryPackage;
import com.devops.backend.model.Package.StandardPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component
public class PackageFactory {
    public DeliveryPackage createPackage(PackageType type) {
        return switch (type) {
            case FRAGILE-> new FragilePackage();
            case LITHIUM -> new LithiumBatteryPackage();
            case STANDARD -> new StandardPackage();
        };
    }
}