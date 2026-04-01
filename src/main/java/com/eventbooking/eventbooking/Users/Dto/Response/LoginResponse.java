package com.eventbooking.eventbooking.Users.Dto.Response;

import java.time.LocalDateTime;

public class LoginResponse {
    
    private String token;
    private String status;
    private LocalDateTime timestamp;
    
    public LoginResponse()
    {

    }

    public LoginResponse(String token, String status, LocalDateTime timestamp) {
        this.token = token;
        this.status = status;
        this.timestamp = timestamp;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
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

    
}
