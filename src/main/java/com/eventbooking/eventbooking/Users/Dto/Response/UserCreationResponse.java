package com.eventbooking.eventbooking.Users.Dto.Response;

import java.time.LocalDateTime;

import com.eventbooking.eventbooking.Users.model.UserRole;

public class UserCreationResponse {
    private String status;
    private LocalDateTime timestamp;
    private UserRole role;

    public UserCreationResponse()
    {
        
    }

    public UserCreationResponse(String status, LocalDateTime timestamp, UserRole role) {
        this.status = status;
        this.timestamp = timestamp;
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
    public UserRole getRole() {
        return role;
    }
    public void setRole(UserRole role) {
        this.role = role;
    }


    
}
