package com.devops.backend.model.Package;

import com.devops.backend.model.CustomType.PackageType;
import com.devops.backend.model.Tracker;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "package_type", discriminatorType = DiscriminatorType.STRING)
@Table(name = "packages")
public abstract class DeliveryPackage{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "tracker_id", referencedColumnName = "id")
    private Tracker tracker;

    @Column(name = "package_type", insertable = false, updatable = false)
    private String packageType;

    private double weight;
    private double height;
    private double width;
    private double length;

    // Getters and Setters
    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public Tracker getTracker(){
        return this.tracker;
    }
    public void setTracker(Tracker tracker){
        this.tracker = tracker;
    }
    public double getWeight(){
        return this.weight;
    }
    public void setWeight(double weight){
        this.weight = weight;
    }
    public double getHeight(){
        return this.height;
    }
    public void setHeight(double height){
        this.height = height;
    }
    public double getWidth(){
        return this.width;
    }
    public void setWidth(double width){
        this.width = width;
    }
    public double getLength(){
        return this.length;
    }
    public void setLength(double length){
        this.length = length;
    }
    public PackageType getPackageType(){return PackageType.valueOf(packageType);}
}



