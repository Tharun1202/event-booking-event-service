package com.eventbooking.eventbooking.Booking.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eventbooking.eventbooking.Booking.Dto.Request.CreateBookingRequest;
import com.eventbooking.eventbooking.Booking.Dto.Response.BookingCancellationResponse;
import com.eventbooking.eventbooking.Booking.Dto.Response.BookingResponse;
import com.eventbooking.eventbooking.Booking.service.BookingService;
import com.eventbooking.eventbooking.Event.Dto.Response.ApiResponse;

@RestController
public class BookingController {
    @Autowired
    BookingService bookingservice;
    
    @PostMapping("/events/book")
    public ResponseEntity<ApiResponse<BookingResponse>> bookEvent(@RequestBody CreateBookingRequest request)
    {
        ApiResponse<BookingResponse> response = new ApiResponse<>();
        response.setData(bookingservice.bookevent(request));
        response.setMessage("Booked event Successfully!!!");
        response.setStatus("SUCCESS");
        response.setStatusCode(201);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/events/bookings")
    public  ResponseEntity<ApiResponse<List<BookingResponse>>> getBookings()
    {
        ApiResponse<List<BookingResponse>> response = new ApiResponse<>();
        response.setData(bookingservice.getBookings());
        response.setMessage("Fetched data from the database...");
        response.setStatus("SUCCESS");
        response.setStatusCode(200);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/events/booking/cancel/{bookingId}")
    public ResponseEntity<ApiResponse<BookingCancellationResponse>> cancelBooking(@PathVariable Integer bookingId)
    {
        ApiResponse<BookingCancellationResponse> response = new ApiResponse<>();
        response.setData(bookingservice.cancelBooking(bookingId));
        response.setMessage("Booking cancelled successfully!!!");
        response.setStatus("SUCCESS");
        response.setStatusCode(201);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }
}
