package com.eventbooking.eventbooking.Event.Dto.Response;

import java.time.LocalDateTime;

public class EventResponse {
    private Integer eventId;
    private String eventName;
    private String artistName;
    private String venue;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private Integer remainingSeats;
    private boolean bookable;
    
    public EventResponse()
    {
        
    }


    public EventResponse(Integer eventId, String eventName, String artistName, String venue,
            LocalDateTime startDateTime, LocalDateTime endDateTime, Integer remainingSeats, boolean bookable) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.artistName = artistName;
        this.venue = venue;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.remainingSeats = remainingSeats;
        this.bookable = bookable;
    }
    public Integer getEventId() {
        return eventId;
    }
    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }
    public String getEventName() {
        return eventName;
    }
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
    public String getArtistName() {
        return artistName;
    }
    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }
    public String getVenue() {
        return venue;
    }
    public void setVenue(String venue) {
        this.venue = venue;
    }
    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }
    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }
    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }
    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }
    public Integer getRemainingSeats() {
        return remainingSeats;
    }
    public void setRemainingSeats(Integer remainingSeats) {
        this.remainingSeats = remainingSeats;
    }
    public boolean isBookable() {
        return bookable;
    }
    public void setBookable(boolean bookable) {
        this.bookable = bookable;
    }

    
    
}
