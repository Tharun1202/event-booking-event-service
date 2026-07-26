package com.eventservice.eventservice.Event.Dto.Response;

public class SeatResponse {
    private ResponseCode status;
    private Integer remainingSeats;

    public SeatResponse()
    {

    }
    public SeatResponse(Integer remainingSeats, ResponseCode status)
    {
        this.remainingSeats = remainingSeats;
        this.status = status; 
    }

    public void setremainingSeats(Integer remainingSeats)
    {
        this.remainingSeats = remainingSeats;
    }

    public void setstatus(ResponseCode eventCancelled)
    {
        this.status = eventCancelled;
    }

    public Integer getremainingSeats()
    {
        return remainingSeats;
    }

    public ResponseCode getstatus()
    {
        return status;
    }
}
