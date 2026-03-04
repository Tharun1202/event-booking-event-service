package com.eventbooking.eventbooking.Event.Dto.Response;

import org.springframework.stereotype.Component;

@Component
public class ApiResponse<T> {
    private String message;
    private String status;
    private Integer statusCode;
    private T data ;
    
    public ApiResponse()
    {

    }
    
    public ApiResponse(String message, String status, Integer statusCode, T data) {
        this.message = message;
        this.status = status;
        this.statusCode = statusCode;
        this.data = data;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Integer getStatusCode() {
        return statusCode;
    }
    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
    

}
