package com.eventservice.eventservice.Event.Controller;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eventservice.eventservice.Event.Dto.Request.CreateEventRequest;
import com.eventservice.eventservice.Event.Dto.Request.SeatRequest;
import com.eventservice.eventservice.Event.Dto.Request.UpdateEventRequest;
import com.eventservice.eventservice.Event.Dto.Response.AdminEventResponse;
import com.eventservice.eventservice.Event.Dto.Response.ApiResponse;
import com.eventservice.eventservice.Event.Dto.Response.EventResponse;
import com.eventservice.eventservice.Event.Dto.Response.SeatResponse;
import com.eventservice.eventservice.Event.service.EventService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
public class EventController {
    
    final EventService eventservice;


    EventController(EventService eventservice) {
        this.eventservice = eventservice;
    }

    

    @GetMapping("/event")
    public ResponseEntity<ApiResponse<List<EventResponse>>> getActiveEventsForUsers()
    {
        ApiResponse<List<EventResponse>> response = new ApiResponse<>();
        response.setData(eventservice.getActiveEventsforUsers());
        response.setStatus("SUCCESS");
        response.setMessage("Events fetched from Database successfully!!");
        response.setStatusCode(200);
        
        return ResponseEntity.status(HttpStatus.OK).body(response);
        
    }

    @GetMapping("/admin/events")
    public ResponseEntity<ApiResponse<List<AdminEventResponse>>> getActiveEventsForAdmins()
    {
        ApiResponse<List<AdminEventResponse>> response = new ApiResponse<>();
        response.setData(eventservice.getActiveEventsForAdmins());
        response.setStatus("SUCCESS");
        response.setMessage("Events fetched from Database successfully!!");
        response.setStatusCode(200);
        
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/admin/create")
    public ResponseEntity<ApiResponse<AdminEventResponse>> createEvents(@Valid @RequestBody CreateEventRequest request)
    {
        ApiResponse<AdminEventResponse> response = new ApiResponse<>();
        response.setData(eventservice.createEvent(request));
        response.setStatus("SUCCESS");
        response.setMessage("Event Created successfully!!");
        response.setStatusCode(201);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/admin/events/{eventId}")
    public ResponseEntity<ApiResponse<AdminEventResponse>> updateEvent(@Valid @RequestBody UpdateEventRequest request , @PathVariable Integer eventId)
    {
        ApiResponse<AdminEventResponse> response = new ApiResponse<>();
        response.setData(eventservice.updateEvent(request, eventId));
        response.setStatus("SUCCESS");
        response.setMessage("Event Updated successfully!!");
        response.setStatusCode(202);
        
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @DeleteMapping("/admin/events/{eventId}")
    public ResponseEntity<ApiResponse> deleteEvent(@PathVariable Integer eventId)
    {
        ApiResponse response = new ApiResponse<>();
        eventservice.deleteEvent(eventId);
        response.setData(null);
        response.setMessage("Data deleted Successfully!!!");
        response.setStatus("SUCCESS");
        response.setStatusCode(204);

        return ResponseEntity.status(HttpStatus.OK).body(response);
         
    }

    @GetMapping("/event/{id}")
    public ResponseEntity<ApiResponse<EventResponse>> getEventById(@PathVariable Integer id)
    {
        ApiResponse<EventResponse> response = new ApiResponse<>();
        response.setData(eventservice.getEventById(id));
        response.setStatusCode(200);
        response.setMessage("Fetching the data from database...");
        response.setStatus("SUCCESS");
        
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/internal/events/{id}/reserve")
    public ResponseEntity< ApiResponse<SeatResponse>> reserveSeats(@Valid @RequestBody SeatRequest request , @PathVariable Integer id)
    {
        ApiResponse<SeatResponse> response = eventservice.reserveSeats(request, id);
         HttpStatus status = HttpStatus.ACCEPTED;
        if(response.getStatusCode()==409)
        {
            status = HttpStatus.CONFLICT;
        }
        else if (response.getStatusCode()==400)
        {
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(response);  
    }

    @PostMapping("/internal/events/{id}/release")
    public ResponseEntity<ApiResponse<SeatResponse>> releaseSeats(HttpServletRequest httpRequest,@Valid @RequestBody SeatRequest request , @PathVariable Integer id)
    {
        Collections.list(httpRequest.getHeaderNames())
            .forEach(name ->
                System.out.println(name + " = " + httpRequest.getHeader(name)));
        System.out.println(LocalDateTime.now() +" Thread=" + Thread.currentThread().getName());
        ApiResponse<SeatResponse> response = eventservice.releaseSeats(request, id);
        HttpStatus status = HttpStatus.ACCEPTED;
        if(response.getStatusCode()==409)
        {
            status = HttpStatus.CONFLICT;
        }
        else if (response.getStatusCode()==400)
        {
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(response);
    }

}
