package com.eventservice.eventservice.Scheduling;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.eventservice.eventservice.Event.model.Event;
import com.eventservice.eventservice.Event.model.EventStatus;
import com.eventservice.eventservice.Event.repository.EventRepository;

import jakarta.transaction.Transactional;


/*
 this should pick up events which are not cancelled and move it to appropriate status
 for ACTIVE , SOLD_OUT  status it should be moved to COMPLETE if time.now > endtime.
 DRAFT , DELETE , COMPLETE , CANCELLED should not be processed
.
SELECT * from event where EventStatus not in (DRAFT , DELETE , COMPLETE , CANCELLED);
*/
@Service
public class EventExpiryScheduler {
    
    final EventRepository eventrepo;
    private static final Logger log = LoggerFactory.getLogger(EventExpiryScheduler.class);

    EventExpiryScheduler(EventRepository eventrepo) {
        this.eventrepo = eventrepo;
    }
   
    @Scheduled(fixedRate = 600000)
    @Transactional
    public void eventExpiryScheduler()
    {
        LocalDateTime time = LocalDateTime.now();
        log.info("Event expiry scheduler started at {}",time);
        List<Event> eventlist =  eventrepo.findEventsforprocessing(List.of(EventStatus.ACTIVE,EventStatus.SOLD_OUT),time);
        for(Event e : eventlist)
        {
            
            e.setStatus(EventStatus.COMPLETED);
            e.setUpdatedAt(LocalDateTime.now());
            
        }
        log.info("Marked {} events as COMPLETED", eventlist.size());
    }
}
