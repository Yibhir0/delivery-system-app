package com.devops.backend.form;

import com.devops.backend.model.CustomType.UserType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserForm {
    @NotNull(message = "First name is required")
    @Size(min = 1, max = 255, message = "First name must be between 1 and 255 characters")
    private String first_name;

    @NotNull(message = "Last name is required")
    @Size(min = 1, max = 255, message = "Last name must be between 1 and 255 characters")
    private String last_name;

    @NotNull(message = "Email is required")
    @Size(min = 1, max = 255, message = "Email must be between 1 and 255 characters")
    private String email;

    @NotNull(message = "Password is required")
    @Size(min = 1, max = 255, message = "Password must be between 1 and 255 characters")
    private String password;

    @NotNull(message = "User type is required")
    UserType userType;

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
    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getPassword(){
        return this.password;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public UserType getUserType() {
        return userType;
    }
    public void setUserType(UserType userType) {
        this.userType = userType;
    }


}
