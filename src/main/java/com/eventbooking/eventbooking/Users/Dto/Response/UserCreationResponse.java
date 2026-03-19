package com.eventbooking.eventbooking.Users.Dto.Response;

import java.time.LocalDateTime;

import com.eventbooking.eventbooking.Users.model.UserRole;

public class UserCreationResponse {
    private String status;
    private LocalDateTime timestamp;
    private String token;
    private UserRole role;

    public UserCreationResponse()
    {
        
    }

    public UserCreationResponse(String status, LocalDateTime timestamp, String token, UserRole role) {
        this.status = status;
        this.timestamp = timestamp;
        this.token = token;
        this.role = role;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public UserRole getRole() {
        return role;
    }
    public void setRole(UserRole role) {
        this.role = role;
    }


    
}
