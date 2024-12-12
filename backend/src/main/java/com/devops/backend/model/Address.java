package com.devops.backend.model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Embeddable
public class Address {
    @NotNull(message = "Number is required")
    private int number;

    @NotNull(message = "Street is required")
    @Size(min = 3, max = 255)
    private String street;

    @NotNull(message = "City is required")
    @Size(min = 3, max = 255)
    private String city;

    @NotNull(message = "Country is required")
    @Size(min = 5, max = 30)
    private String country;

    @NotNull(message = "ZipCode is required")
    @Size(min = 5, max = 10, message = "ZipCode must be between 5 and 10 characters")
    private String ZipCode;

    public String getZipCode() {
        return ZipCode;
    }

    public void setZipCode(String zipCode) {
        ZipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

}
