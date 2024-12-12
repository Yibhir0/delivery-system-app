package com.devops.backend.model;

import com.devops.backend.model.CustomType.UserType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String first_name;
    @NotNull
    private String last_name;
    @Embedded
    private Address address;
    private long phone_number;
    @NotNull
    private UserType userType;
    @Column(unique = true)
    @NotNull
    private String email;
    @NotNull
    private String password;
    private boolean isActive = true;

    // Getters and Setters
    public Long getId() {
        return id;
        }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFirst_name() {
        return first_name;
    }
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }
    public String getLast_name() {
        return last_name;
    }
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
    public String getFullName() {
        return this.first_name + " " + this.last_name;
    }
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
    public long getPhone_number() {
        return phone_number;
    }
    public void setPhone_number(long phone_number) {
        this.phone_number = phone_number;
    }
    public UserType getUserType() {
        return userType;
    }
    public void setUserType(UserType userType) {
        this.userType = userType;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public boolean isActive() {
        return isActive;
    }
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }




    
}
