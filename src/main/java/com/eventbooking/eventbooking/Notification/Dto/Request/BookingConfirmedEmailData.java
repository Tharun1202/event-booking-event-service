package com.eventbooking.eventbooking.Notification.Dto.Request;

import java.time.LocalDate;

public class BookingConfirmedEmailData {
    
    private String username;
    private Integer bookingId;
    private String eventName;
    private LocalDate eventDate;
    private Integer seatCount;

    public BookingConfirmedEmailData()
    {

    }

    public BookingConfirmedEmailData(String username, Integer bookingId, String eventName, LocalDate eventDate,
            Integer seatCount) {
        this.username = username;
        this.bookingId = bookingId;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.seatCount = seatCount;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public Integer getBookingId() {
        return bookingId;
    }
    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }
    public String getEventName() {
        return eventName;
    }
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
    public LocalDate getEventDate() {
        return eventDate;
    }
    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }
    public Integer getSeatCount() {
        return seatCount;
    }
    public void setSeatCount(Integer seatCount) {
        this.seatCount = seatCount;
    }


    
}
