package com.eventbooking.eventbooking.Dto.Response;

import java.time.LocalDateTime;

import com.eventbooking.eventbooking.model.EventStatus;

public class AdminEventResponse {
    private Integer eventId;
    private String eventName;
    private String artistName;
    private String venue;
    private Integer totalCapacity;
    private Integer bookedCount;
    private EventStatus status;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public AdminEventResponse()
    {
        
    }

    public AdminEventResponse(Integer eventId, String eventName, String artistName, String venue, Integer totalCapacity,
            Integer bookedCount, EventStatus status, LocalDateTime startDateTime, LocalDateTime endDateTime,
            LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.artistName = artistName;
        this.venue = venue;
        this.totalCapacity = totalCapacity;
        this.bookedCount = bookedCount;
        this.status = status;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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
    public Integer getTotalCapacity() {
        return totalCapacity;
    }
    public void setTotalCapacity(Integer totalCapacity) {
        this.totalCapacity = totalCapacity;
    }
    public Integer getBookedCount() {
        return bookedCount;
    }
    public void setBookedCount(Integer bookedCount) {
        this.bookedCount = bookedCount;
    }
    public EventStatus getStatus() {
        return status;
    }
    public void setStatus(EventStatus status) {
        this.status = status;
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
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    

    
}
