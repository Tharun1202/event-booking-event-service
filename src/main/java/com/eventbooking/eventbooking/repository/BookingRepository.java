package com.eventbooking.eventbooking.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.eventbooking.eventbooking.model.Booking;
import com.eventbooking.eventbooking.model.BookingStatus;

public interface BookingRepository extends JpaRepository<Booking,Integer>{
    
    @Query("Select b from Booking b where b.status = :status and b.expiryTime <= :time")
    List<Booking> findExpiredBooking(@Param("status") BookingStatus status,@Param("time") LocalDateTime time);

}
