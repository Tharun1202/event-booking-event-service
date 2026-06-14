package com.eventbooking.eventbooking.Notification.Dto.Request;

import java.time.LocalDateTime;

public class RefundEmailData {
    
    private String username;
    private Integer paymentId;
    private Integer bookingId;
    private Double amount;
    private LocalDateTime date;
    private String eventName;


    public void setUsername(String username)
    {
        this.username = username;
    }
    public String getUsername()
    {
        return username;
    }
    public void setPaymentId(Integer paymentId)
    {
        this.paymentId = paymentId;
    }
    public Integer getPaymentId()
    {
        return paymentId;
    }
    public void setBookingId(Integer bookingId)
    {
        this.bookingId = bookingId;
    }
    public Integer getBookingId()
    {
        return bookingId;
    }
    public void setAmount(Double amount)
    {
        this.amount = amount;
    }
    public Double getAmount()
    {
        return amount;
    }
    public LocalDateTime getDate()
    {
        return date;
    }
    public void setDate(LocalDateTime date)
    {
        this.date = date;
    }
    public String geteventName()
    {
        return eventName;
    }
    public void seteventName(String eventName)
    {
        this.eventName = eventName;
    }

}
