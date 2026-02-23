package com.eventbooking.eventbooking.Scheduling;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.eventbooking.eventbooking.model.Booking;
import com.eventbooking.eventbooking.model.BookingStatus;
import com.eventbooking.eventbooking.model.Event;
import com.eventbooking.eventbooking.model.EventStatus;
import com.eventbooking.eventbooking.repository.BookingRepository;
import com.eventbooking.eventbooking.repository.EventRepository;

import jakarta.transaction.Transactional;

@Service
public class BookingExpiryScheduler {
    @Autowired
    BookingRepository bookrepo;
    @Autowired 
    EventRepository eventrepo;
    
    @Scheduled(fixedRate = 300000)
    @Transactional
    public void expirePendingTransaction()  // this method would expire transactions if expire_ts > now && status == PENDING.
    {
        LocalDateTime time = LocalDateTime.now();
        System.out.println("Scheduler started at "+time.format(DateTimeFormatter.ofPattern("dd-mm-yyyy HH:mm:ss"))+"....");
        List<Booking> result = bookrepo.findExpiredBooking(BookingStatus.PENDING, time);
        for(Booking b : result)
        {
            if(b.getStatus()!=BookingStatus.PENDING){
                continue;
            }
            Event event = eventrepo.findByEventId(b.getEvent().getEventId()).orElseThrow();
            int count = event.getBookedCount()-b.getQuantity();
            if(count<0){
                throw new IllegalArgumentException("booking count cannot be less than zero");
            }
            event.setBookedCount(count);
            if(event.getBookedCount()<event.getTotalCapacity())
            {
                event.setStatus(EventStatus.ACTIVE);
            }
            b.setStatus(BookingStatus.EXPIRED);
        }

    }

}
