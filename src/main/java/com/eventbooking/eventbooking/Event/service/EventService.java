package com.eventbooking.eventbooking.Event.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.eventbooking.eventbooking.Event.Dto.Request.CreateEventRequest;
import com.eventbooking.eventbooking.Event.Dto.Request.UpdateEventRequest;
import com.eventbooking.eventbooking.Event.Dto.Response.AdminEventResponse;
import com.eventbooking.eventbooking.Event.Dto.Response.EventResponse;
import com.eventbooking.eventbooking.Event.model.Event;
import com.eventbooking.eventbooking.Event.model.EventStatus;
import com.eventbooking.eventbooking.Event.repository.EventRepository;
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

    public List<EventResponse> getActiveEventsforUsers()
    {
        List<Event> events = eventrepo.findByStatusAndEndDateTimeAfterOrderByStartDateTimeAsc(EventStatus.ACTIVE,LocalDateTime.now());
        List<EventResponse> response = new ArrayList<>();
        for(Event e : events )
        {   
            int remainingSeats = 0;
            boolean isAvailable = false ;
            if(e.getBookedCount()<e.getTotalCapacity())
            {
                isAvailable=true;
                remainingSeats = e.getTotalCapacity() - e.getBookedCount();
            }
            EventResponse resp = new EventResponse();
            resp.setArtistName(e.getArtistName());
            resp.setEventId(e.getEventId());
            resp.setEventName(e.getEventName());
            resp.setStartDateTime(e.getStartDateTime());
            resp.setEndDateTime(e.getEndDateTime());
            resp.setBookable(isAvailable);
            resp.setRemainingSeats(remainingSeats);
            resp.setVenue(e.getVenue());
            response.add(resp);
        }


        return response;
    }

    public EventResponse getEventById(Integer Id)
    {
        Event event = eventrepo.findById(Id).get();
        boolean isAvailable = false;
        int seats = 0;
        if(event==null)
        {
            throw new IllegalArgumentException("Event id not found");
        }
        if(event.getBookedCount()<event.getTotalCapacity())
        {
            isAvailable= true;
            seats = event.getTotalCapacity() - event.getBookedCount();
        }
        EventResponse response = new EventResponse();
        response.setEventId(Id);
        response.setArtistName(event.getArtistName());
        response.setEventName(event.getEventName());
        response.setVenue(event.getVenue());
        response.setBookable(isAvailable);
        response.setRemainingSeats(seats);
        response.setStartDateTime(event.getStartDateTime());
        response.setEndDateTime(event.getEndDateTime());

        return response;

    }

    public List<AdminEventResponse> getActiveEventsForAdmins()
    {
        List<Event> event = eventrepo.findByStatusAndEndDateTimeAfterOrderByStartDateTimeAsc(EventStatus.ACTIVE,LocalDateTime.now());
        List<AdminEventResponse> response = new ArrayList<>();
        for(Event e : event)
        {
            AdminEventResponse resp = new AdminEventResponse();
            resp.setEventId(e.getEventId());
            resp.setEventName(e.getEventName());
            resp.setArtistName(e.getArtistName());
            resp.setVenue(e.getVenue());
            resp.setStartDateTime(e.getStartDateTime());
            resp.setEndDateTime(e.getEndDateTime());
            resp.setBookedCount(e.getBookedCount());
            resp.setTotalCapacity(e.getTotalCapacity());
            resp.setCreatedAt(e.getCreatedAt());
            resp.setUpdatedAt(e.getUpdatedAt());
            resp.setStatus(e.getStatus());
            
            response.add(resp);
        }
        return response;
    }

    public AdminEventResponse updateEvent(UpdateEventRequest request , Integer Eventid)
    {
        Event event = eventrepo.findById(Eventid)
        .orElseThrow(() -> new IllegalArgumentException("Event not found"));
        
        if(event.getStatus() == EventStatus.CANCELLED ||event.getStatus() == EventStatus.COMPLETED)
        {
            throw new IllegalArgumentException("Cannot update this event");
        }
        if(!request.getEventName().trim().isBlank())
        {
            event.setEventName(request.getEventName());
        }
        if(!request.getArtistName().trim().isBlank())
        {
            event.setArtistName(request.getArtistName());
        }
        if(!request.getVenue().trim().isBlank())
        {
            event.setVenue(request.getVenue());
        }
        if(request.getStartDateTime()!=null)
        {
            event.setStartDateTime(request.getStartDateTime());
        }
        if(request.getEndDateTime()!=null)
        {
            event.setEndDateTime(request.getEndDateTime());
        }
        if(request.getTotalCapacity()!=null && request.getTotalCapacity()>0)
        {
            if(request.getTotalCapacity() < event.getBookedCount()) 
            {
                throw new IllegalArgumentException("Capacity cannot be less than booked seats");
            }
            event.setTotalCapacity(request.getTotalCapacity());
        }
        event.setUpdatedAt(timeStamp());
        eventrepo.save(event);
        AdminEventResponse response = new AdminEventResponse();
        response.setEventId(Eventid);
        response.setArtistName(event.getArtistName());
        response.setEventName(event.getEventName());
        response.setVenue(event.getVenue());
        response.setCreatedAt(event.getCreatedAt());
        response.setUpdatedAt(event.getUpdatedAt());
        response.setBookedCount(event.getBookedCount());
        response.setStartDateTime(event.getStartDateTime());
        response.setEndDateTime(event.getEndDateTime());
        response.setStatus(event.getStatus());
        response.setTotalCapacity(event.getTotalCapacity());
        
        return response;
    }

    public void deleteEvent(Integer eventId)
    {
        Optional<Event> e = eventrepo.findById(eventId);
        if(!e.isPresent())
        {
            throw new IllegalArgumentException("Event does not exists");
        }
        Event event = e.get();
        event.setStatus(EventStatus.DELETED);
        eventrepo.save(event);

    }

    public LocalDateTime timeStamp()
    {
        return LocalDateTime.now();
    }
}
