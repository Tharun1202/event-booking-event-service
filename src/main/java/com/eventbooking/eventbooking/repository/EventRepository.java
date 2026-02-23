package com.eventbooking.eventbooking.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import com.eventbooking.eventbooking.model.Event;
import com.eventbooking.eventbooking.model.EventStatus;
import jakarta.persistence.LockModeType;


public interface EventRepository extends JpaRepository<Event,Integer>{ 
 List<Event>findByStatusAndEndDateTimeAfterOrderByStartDateTimeAsc(EventStatus status , LocalDateTime time);
 List<Event>findByStatusAndEndDateTimeLessThanEqual(EventStatus status,LocalDateTime time);
 @Lock(LockModeType.PESSIMISTIC_WRITE)
 Optional<Event> findByEventId(Integer EventId);
}
