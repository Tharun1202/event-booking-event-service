package com.eventservice.eventservice.Event.Dto.Request;

import jakarta.validation.constraints.NotNull;

public class SeatRequest {
    
    @NotNull
    private Integer quantity;

    public SeatRequest()
    {

    }
    public SeatRequest(Integer quantity )
    {
        this.quantity = quantity;
    }
    public void setQuantity(Integer quantity)
    {
        this.quantity = quantity;
    }
    public Integer getQuantity()
    {
        return quantity;
    }
}
