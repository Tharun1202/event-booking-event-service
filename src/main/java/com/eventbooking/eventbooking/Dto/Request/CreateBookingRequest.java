package com.eventbooking.eventbooking.Dto.Request;

public class CreateBookingRequest {
    private Integer eventId;
    private Integer userId;
    private Integer quantity;

    public CreateBookingRequest()
    {

    }

    public CreateBookingRequest(Integer eventId, Integer userId, Integer quantity) {
        this.eventId = eventId;
        this.userId = userId;
        this.quantity = quantity;
    }
    
    public Integer getEventId() {
        return eventId;
    }
    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    
    
}
