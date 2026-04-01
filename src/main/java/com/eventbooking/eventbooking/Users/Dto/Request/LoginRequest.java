package com.eventbooking.eventbooking.Users.Dto.Request;

import jakarta.validation.constraints.NotBlank;

public class LoginRequest {
    
    @NotBlank
    private String username;
    @NotBlank
    private String Password;

    public LoginRequest()
    {
        
    }

    public LoginRequest(String username, String password) {
        this.username = username;
        Password = password;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return Password;
    }
    public void setPassword(String password) {
        Password = password;
    }

    
    
}
