package com.eventbooking.eventbooking.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="booking")
public class Booking {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookingId;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(nullable = false ,name ="eventId")
    private Event event;
    @Column(nullable = false)
    private Integer userId;
    @Column(nullable = false)
    private Integer quantity;
    @Column(nullable = false)
    private LocalDateTime bookingTime;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private BookingStatus status;
    @Column(nullable = false)
    private LocalDateTime expiryTime;

    public Booking()
    {

    }
    
    public Booking(Integer bookingId, Event event, Integer userId, Integer quantity, LocalDateTime bookingTime,
            BookingStatus status,LocalDateTime expiryTime) {
        this.bookingId = bookingId;
        this.event = event;
        this.userId = userId;
        this.quantity = quantity;
        this.bookingTime = bookingTime;
        this.status = status;
        this.expiryTime = expiryTime;
    }
    public Integer getBookingId() {
        return bookingId;
    }
    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }
    public Event getEvent() {
        return event;
    }
    public void setEvent(Event event) {
        this.event = event;
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
    public LocalDateTime getBookingTime() {
        return bookingTime;
    }
    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }
    public BookingStatus getStatus() {
        return status;
    }
    public void setStatus(BookingStatus status) {
        this.status = status;
    }
    public LocalDateTime getExpiryTime() {
        return expiryTime;
    }
    public void setExpiryTime(LocalDateTime expiryTime) {
        this.expiryTime = expiryTime;
    }
    
}
