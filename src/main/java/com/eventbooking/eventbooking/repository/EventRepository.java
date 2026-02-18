package com.eventbooking.eventbooking.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eventbooking.eventbooking.model.Event;
import com.eventbooking.eventbooking.model.EventStatus;

public interface EventRepository extends JpaRepository<Event,Integer>{ 
 List<Event>findByStatusAndEndDateTimeAfterOrderByStartDateTimeAsc(EventStatus status , LocalDateTime time);
 List<Event>findByStatusAndEndDateTimeLessThanEqual(EventStatus status,LocalDateTime time);
}
