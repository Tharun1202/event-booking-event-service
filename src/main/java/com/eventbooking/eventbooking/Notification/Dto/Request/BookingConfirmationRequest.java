package com.eventbooking.eventbooking.Notification.Dto.Request;

import com.eventbooking.eventbooking.Booking.models.Booking;
import com.eventbooking.eventbooking.Users.model.User;

import jakarta.annotation.Nonnull;

public class BookingConfirmationRequest {
    @Nonnull
    private User user;
    @Nonnull
    private Booking booking;

    public BookingConfirmationRequest()
    {

    }    

    public BookingConfirmationRequest(User user, Booking booking) {
        this.user = user;
        this.booking = booking;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Booking getBooking() {
        return booking;
    }
    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    
    
}
