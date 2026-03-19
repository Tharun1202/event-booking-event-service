package com.eventbooking.eventbooking.Users.Dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateAdminUser {
    
    @NotNull
    private Integer userId;
    @NotBlank
    private String username;
    @NotBlank
    private String email;
    @NotBlank
    private String Password;

    public CreateAdminUser()
    {

    }

    public CreateAdminUser(Integer userId,String username,String email,String password)
    {
        this.userId = userId;
        this.username = username;
        this.email = email;
        Password = password;
    }

    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return Password;
    }
    public void setPassword(String password) {
        Password = password;
    }

    
}
