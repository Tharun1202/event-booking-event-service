package com.eventbooking.eventbooking.Controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eventbooking.eventbooking.Dto.Request.CreateEventRequest;
import com.eventbooking.eventbooking.Dto.Request.UpdateEventRequest;
import com.eventbooking.eventbooking.Dto.Response.AdminEventResponse;
import com.eventbooking.eventbooking.Dto.Response.ApiResponse;
import com.eventbooking.eventbooking.Dto.Response.EventResponse;
import com.eventbooking.eventbooking.service.EventService;

@RestController
public class EventController {
    
    @Autowired
    EventService eventservice;

    

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
    public ResponseEntity<ApiResponse<AdminEventResponse>> createEvents(@RequestBody CreateEventRequest request)
    {
        ApiResponse<AdminEventResponse> response = new ApiResponse<>();
        response.setData(eventservice.createEvent(request));
        response.setStatus("SUCCESS");
        response.setMessage("Event Created successfully!!");
        response.setStatusCode(201);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/admin/events/{eventId}")
    public ResponseEntity<ApiResponse<AdminEventResponse>> updateEvent(@RequestBody UpdateEventRequest request , @PathVariable Integer eventId)
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

}
