package com.devops.backend.config;

import com.devops.backend.model.Package.FragilePackage;
import com.devops.backend.model.Package.LithiumBatteryPackage;
import com.devops.backend.model.Package.StandardPackage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PackageConfig {

    @Bean
    public StandardPackage standardPackage() {
        return new StandardPackage();
    }

    @Bean
    public FragilePackage fragilePackage() {
        return new FragilePackage();
    }

    @Bean
    public LithiumBatteryPackage lithiumBatteryPackage() {
        return new LithiumBatteryPackage();
    }
}