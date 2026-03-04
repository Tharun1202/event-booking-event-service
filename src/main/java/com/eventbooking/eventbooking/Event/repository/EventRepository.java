package com.eventbooking.eventbooking.Event.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.eventbooking.eventbooking.Event.model.Event;
import com.eventbooking.eventbooking.Event.model.EventStatus;

import jakarta.persistence.LockModeType;


public interface EventRepository extends JpaRepository<Event,Integer>{ 
 List<Event>findByStatusAndEndDateTimeAfterOrderByStartDateTimeAsc(EventStatus status , LocalDateTime time);
 List<Event>findByStatusAndEndDateTimeLessThanEqual(EventStatus status,LocalDateTime time);
 @Lock(LockModeType.PESSIMISTIC_WRITE)
 Optional<Event> findByEventId(Integer EventId);
 @Query("SELECT e from Event e where e.status in :excludelist and e.endDateTime <= :time")
 List<Event>findEventsforprocessing(@Param("excludelist") List<EventStatus> excludelist, @Param("time") LocalDateTime time);
}
