package com.eventbooking.eventbooking.Dto.Response;

import java.time.LocalDateTime;

import com.eventbooking.eventbooking.model.BookingStatus;

public class BookingResponse {
    private Integer bookingId;
    private Integer eventId;
    private String eventName;
    private String artistName;
    private Integer quantity;
    private BookingStatus status;
    private LocalDateTime bookingTime;

    public BookingResponse()
    {

    }

    public BookingResponse(Integer bookingId, Integer eventId, String eventName, String artistName, Integer quantity,
            BookingStatus status, LocalDateTime bookingTime) {
        this.bookingId = bookingId;
        this.eventId = eventId;
        this.eventName = eventName;
        this.artistName = artistName;
        this.quantity = quantity;
        this.status = status;
        this.bookingTime = bookingTime;
    }
    public Integer getBookingId() {
        return bookingId;
    }
    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }
    public Integer getEventId() {
        return eventId;
    }
    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }
    public String getEventName() {
        return eventName;
    }
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
    public String getArtistName() {
        return artistName;
    }
    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public BookingStatus getStatus() {
        return status;
    }
    public void setStatus(BookingStatus status) {
        this.status = status;
    }
    public LocalDateTime getBookingTime() {
        return bookingTime;
    }
    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }


    
    

}
