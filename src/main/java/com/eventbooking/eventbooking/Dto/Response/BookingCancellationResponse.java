package com.eventbooking.eventbooking.Dto.Response;

import java.time.LocalDateTime;

import com.eventbooking.eventbooking.model.BookingStatus;
import com.eventbooking.eventbooking.model.EventStatus;

public class BookingCancellationResponse {
   private Integer bookingId;
   private Integer eventId;
   private BookingStatus bookingStatus;
   private Integer releasedSeats;
   private EventStatus eventStatus;
   private boolean refundInitiated;
   private LocalDateTime cancelledAt;

   public BookingCancellationResponse()
   {
    
   }

   public BookingCancellationResponse(Integer bookingId, Integer eventId, BookingStatus bookingStatus,
        Integer releasedSeats, EventStatus eventStatus, boolean refundInitiated, LocalDateTime cancelledAt) {
    this.bookingId = bookingId;
    this.eventId = eventId;
    this.bookingStatus = bookingStatus;
    this.releasedSeats = releasedSeats;
    this.eventStatus = eventStatus;
    this.refundInitiated = refundInitiated;
    this.cancelledAt = cancelledAt;
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
   public BookingStatus getBookingStatus() {
    return bookingStatus;
   }
   public void setBookingStatus(BookingStatus bookingStatus) {
    this.bookingStatus = bookingStatus;
   }
   public Integer getReleasedSeats() {
    return releasedSeats;
   }
   public void setReleasedSeats(Integer releasedSeats) {
    this.releasedSeats = releasedSeats;
   }
   public EventStatus getEventStatus() {
    return eventStatus;
   }
   public void setEventStatus(EventStatus eventStatus) {
    this.eventStatus = eventStatus;
   }
   public boolean isRefundInitiated() {
    return refundInitiated;
   }
   public void setRefundInitiated(boolean refundInitiated) {
    this.refundInitiated = refundInitiated;
   }
   public LocalDateTime getCancelledAt() {
    return cancelledAt;
   }
   public void setCancelledAt(LocalDateTime cancelledAt) {
    this.cancelledAt = cancelledAt;
   }

   
}
