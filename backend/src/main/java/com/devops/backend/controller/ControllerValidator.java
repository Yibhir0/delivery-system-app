package com.devops.backend.controller;

import com.devops.backend.model.Address;
import org.springframework.stereotype.Component;

@Component
public class ControllerValidator {
    public static boolean isValidString(String name) {
        return name != null && !name.trim().isEmpty() && name.matches("[a-zA-Z]+");
    }

    public static boolean isValidAddress(Address address) {
        return address != null &&
                address.getStreet() != null && !address.getStreet().trim().isEmpty() && address.getStreet().matches("[a-zA-Z0-9]+") &&
                address.getCity() != null && !address.getCity().trim().isEmpty() && address.getCity().matches("[a-zA-Z]+") &&
                address.getCountry() != null && !address.getCountry().trim().isEmpty() && address.getCountry().matches("[a-zA-Z]+") &&
                address.getZipCode() != null && !address.getZipCode().trim().isEmpty() &&
                address.getNumber() > 0;
    }

    public static boolean isValidDouble(double value) {
        return value > 0;
    }

    public static boolean isValidPhoneNumber(long value) {
        return value >= 1000000000 && value <= 9999999999L;
    }

    public static boolean isValidEmail(String email) {
        return email != null && email.trim().matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$");
    }

}
