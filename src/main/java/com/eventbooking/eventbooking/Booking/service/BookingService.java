package com.eventbooking.eventbooking.Booking.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventbooking.eventbooking.Booking.Dto.Request.CreateBookingRequest;
import com.eventbooking.eventbooking.Booking.Dto.Response.BookingCancellationResponse;
import com.eventbooking.eventbooking.Booking.Dto.Response.BookingResponse;
import com.eventbooking.eventbooking.Booking.models.Booking;
import com.eventbooking.eventbooking.Booking.models.BookingStatus;
import com.eventbooking.eventbooking.Booking.repository.BookingRepository;
import com.eventbooking.eventbooking.Event.model.Event;
import com.eventbooking.eventbooking.Event.model.EventStatus;
import com.eventbooking.eventbooking.Event.repository.EventRepository;
import com.eventbooking.eventbooking.Users.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class BookingService {
    @Autowired
    BookingRepository bookingrepo;
    @Autowired
    EventRepository eventrepo;
    @Autowired
    UserRepository userrepo;
    
    
    @Transactional
    public BookingResponse bookevent(CreateBookingRequest request)
    {
        if(userrepo.findById(request.getUserId()).isEmpty())
        {
            throw new IllegalArgumentException("userId doesn't exists");
        }
        Event event = eventrepo.findByEventId(request.getEventId()).orElseThrow();
        Booking book = new Booking();
        if(event.getStatus()==EventStatus.ACTIVE)
        {
            int capacity =  event.getBookedCount()+request.getQuantity();
            if(capacity>event.getTotalCapacity())
            {
                throw new RuntimeException("requested Quantity is exceeding the total capacity. try again with a lower quantity...");
            }
            else{
                event.setBookedCount(capacity);
                if(capacity==event.getTotalCapacity())
                {
                     event.setStatus(EventStatus.SOLD_OUT);
                }
                
                book.setEvent(event);
                book.setQuantity(request.getQuantity());
                book.setStatus(BookingStatus.PENDING);
                book.setUserId(request.getUserId());
                LocalDateTime time = timestamp(); 
                book.setBookingTime(time);
                book.setExpiryTime(time.plusMinutes(10));
                bookingrepo.save(book);
            }
        }
        else{
            throw new RuntimeException("event is not Bookable");
        }
    BookingResponse response = new BookingResponse();
    response.setEventId(request.getEventId());
    response.setBookingId(book.getBookingId());
    response.setArtistName(event.getArtistName());
    response.setEventName(event.getEventName());
    response.setQuantity(request.getQuantity());
    response.setStatus(book.getStatus());
    response.setBookingTime(book.getBookingTime());
    
    return response;
    
    }

    public List<BookingResponse> getBookings()
    {
        List<Booking> res = bookingrepo.findAll();
        List<BookingResponse> response = new ArrayList<>();
        for(Booking b : res)
        {
            BookingResponse resp = new BookingResponse();
            resp.setBookingId(b.getBookingId());
            resp.setArtistName(b.getEvent().getArtistName());
            resp.setEventId(b.getEvent().getEventId());
            resp.setBookingId(b.getBookingId());
            resp.setEventName(b.getEvent().getEventName());
            resp.setQuantity(b.getQuantity());
            resp.setStatus(b.getStatus());
            resp.setBookingTime(timestamp());

            response.add(resp);
        }
        return response;
    }

    /*
    for cancel API i should lock the events and then update the bookingcount to prevent race condition. after updating the count i need to update the booking to cancel.
    edge cases:
    if the event is SOLD_OUT then it should be changed to ACTIVE .
    if the event is CANCELLED then the payment for the booking should be refunded . So we can't cancell a booking for CANCELLED event.

    */
   @Transactional
   public BookingCancellationResponse cancelBooking(Integer bookingId)
   {
    Booking book = bookingrepo.findById(bookingId).orElseThrow();
    Event event = eventrepo.findByEventId(book.getEvent().getEventId()).orElseThrow();
    boolean refund = false;
        if(event.getStatus()==EventStatus.COMPLETED)
        {
            throw new IllegalStateException("Event already completed");
        }
        else if(book.getStatus()==BookingStatus.EXPIRED)
        {
            throw new IllegalArgumentException("Cannot cancel an expired booking!!!");
        }
        else if(book.getStatus()==BookingStatus.CANCELLED)
        {
            return new BookingCancellationResponse();
        }
        else if(book.getStatus()==BookingStatus.PENDING)
        {
            event.setBookedCount(event.getBookedCount()-book.getQuantity());
            if(event.getTotalCapacity()>event.getBookedCount())
            {
                event.setStatus(EventStatus.ACTIVE);
            }
            book.setStatus(BookingStatus.CANCELLED);
        }
        else if(book.getStatus()==BookingStatus.COMPLETED)
        {
            event.setBookedCount(event.getBookedCount()-book.getQuantity());
            if(event.getTotalCapacity()>event.getBookedCount())
            {
                event.setStatus(EventStatus.ACTIVE);
            }
            book.setStatus(BookingStatus.CANCELLED);
            // TO-DO initiate refund for this booking.
            refund = true;
        }
        BookingCancellationResponse response = new BookingCancellationResponse();
        response.setBookingId(bookingId);
        response.setBookingStatus(BookingStatus.CANCELLED);
        response.setCancelledAt(timestamp());
        response.setEventId(event.getEventId());
        response.setEventStatus(event.getStatus());
        response.setRefundInitiated(refund);
        response.setReleasedSeats(book.getQuantity());

        return response;

   }


    public LocalDateTime timestamp()
    {
        return LocalDateTime.now();
    }
}
