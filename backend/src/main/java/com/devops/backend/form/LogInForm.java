package com.devops.backend.form;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class LogInForm {
    @NotNull(message = "Email is required")
    @Size(min = 1, max = 255, message = "Email must be between 1 and 255 characters")
    private String email;

    @NotNull(message = "Password is required")
    @Size(min = 1, max = 255, message = "Password must be between 1 and 255 characters")
    private String password;

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
}
