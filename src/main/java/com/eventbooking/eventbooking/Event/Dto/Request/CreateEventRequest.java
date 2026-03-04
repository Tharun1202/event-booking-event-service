package com.eventbooking.eventbooking.Event.Dto.Request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;

public class CreateEventRequest {
    @NotBlank
    private String eventName;
    @NotBlank
    private String artistName;
    @NotBlank
    private String venue;
    @NotBlank
    private Integer totalCapacity;
    @NotBlank
    private LocalDateTime startDateTime;
    @NotBlank
    private LocalDateTime endDateTime;
    
    public CreateEventRequest()
    {

    }

    public CreateEventRequest(String eventName, String artistName, String venue, Integer totalCapacity,
            LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.eventName = eventName;
        this.artistName = artistName;
        this.venue = venue;
        this.totalCapacity = totalCapacity;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
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
    public Integer getTotalCapacity() {
        return totalCapacity;
    }
    public void setTotalCapacity(Integer totalCapacity) {
        this.totalCapacity = totalCapacity;
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

    

}
