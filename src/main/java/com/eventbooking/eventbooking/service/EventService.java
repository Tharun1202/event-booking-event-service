package com.eventbooking.eventbooking.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventbooking.eventbooking.Dto.Request.CreateEventRequest;
import com.eventbooking.eventbooking.Dto.Response.AdminEventResponse;
import com.eventbooking.eventbooking.Dto.Response.EventResponse;
import com.eventbooking.eventbooking.model.Event;
import com.eventbooking.eventbooking.model.EventStatus;
import com.eventbooking.eventbooking.repository.EventRepository;

import jakarta.transaction.Transactional;

@Service
public class EventService {
    @Autowired
    EventRepository eventrepo;

    @Transactional
    public AdminEventResponse createEvent(CreateEventRequest request)
    {
        /*
        get the request dto and then validate the data from the request like startDateTime > now , endDateTime > startDateTime  , totalCapacity > 0;
        set appropriate default values to the fields and then save the 
        */
       if(!request.getStartDateTime().isAfter(LocalDateTime.now()))
       {
         throw new IllegalArgumentException("startDateTime should be greater than current time");
       }
       if(!request.getEndDateTime().isAfter(request.getStartDateTime()))
       {
        throw new IllegalArgumentException("EndDateTime is before StartDateTime");        
       }
       if(request.getTotalCapacity()<=0)
       {
        throw new IllegalArgumentException("Capacity should be a positive number greater than or equal to 1");
       }

       Event e = new Event();
       e.setEventName(request.getEventName());
       e.setArtistName(request.getArtistName());
       e.setVenue(request.getVenue());
       e.setBookedCount(0);
       e.setTotalCapacity(request.getTotalCapacity());
       e.setStartDateTime(request.getStartDateTime());
       e.setEndDateTime(request.getEndDateTime());
       e.setStatus(EventStatus.ACTIVE);
       LocalDateTime time = timeStamp();
       e.setCreatedAt(time);
       e.setUpdatedAt(time);

       eventrepo.save(e);

       AdminEventResponse response = new AdminEventResponse();
       response.setEventId(e.getEventId());
       response.setEventName(e.getEventName());
       response.setArtistName(e.getArtistName());
       response.setBookedCount(e.getBookedCount());
       response.setCreatedAt(e.getCreatedAt());
       response.setUpdatedAt(e.getUpdatedAt());
       response.setEventName(e.getEventName());
       response.setStartDateTime(e.getStartDateTime());
       response.setEndDateTime(e.getEndDateTime());
       response.setStatus(e.getStatus());
       response.setTotalCapacity(e.getTotalCapacity());
       response.setVenue(e.getVenue());
       
       return response;
    } 

    public List<EventResponse> getAllEvent()
    {
        
    }

    public LocalDateTime timeStamp()
    {
        return LocalDateTime.now();
    }
}
