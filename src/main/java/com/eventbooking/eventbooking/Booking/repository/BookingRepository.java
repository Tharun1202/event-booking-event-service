package com.eventbooking.eventbooking.Booking.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.eventbooking.eventbooking.Booking.models.Booking;
import com.eventbooking.eventbooking.Booking.models.BookingStatus;

import jakarta.persistence.LockModeType;

public interface BookingRepository extends JpaRepository<Booking,Integer>{
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Booking> findByBookingId(Integer bookingId);
    @Query("Select b from Booking b where b.status = :status and b.expiryTime <= :time")
    List<Booking> findExpiredBooking(@Param("status") BookingStatus status,@Param("time") LocalDateTime time);

}
