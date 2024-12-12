package com.devops.backend.form;

import com.devops.backend.model.CustomType.PackageType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class PackageForm {
    @NotNull(message = "Weight is required")
    @Positive(message = "Weight must be greater than 0")
    private double weight;
    @NotNull(message = "Height is required")
    @Positive(message = "Height must be greater than 0")
    private double height;
    @NotNull(message = "Width is required")
    @Positive(message = "Width must be greater than 0")
    private double width;
    @NotNull(message = "Length is required")
    @Positive(message = "Length must be greater than 0")
    private double length;
    @NotNull(message = "Package type is required")
    private PackageType packageType;

    public double getWeight() {
        return weight;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }
    public double getHeight() {
        return height;
    }
    public void setHeight(double height) {
        this.height = height;
    }
    public double getWidth() {
        return width;
    }
    public void setWidth(double width) {
        this.width = width;
    }
    public double getLength() {
        return length;
    }
    public void setLength(double length) {
        this.length = length;
    }
    public PackageType getPackageType() {
        return packageType;
    }
    public void setPackageType(PackageType packageType) {
        this.packageType = packageType;
    }
}
