package com.eventbooking.eventbooking.Payments.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.eventbooking.eventbooking.Payments.model.Payment;
import com.eventbooking.eventbooking.Payments.model.PaymentStatus;

public interface PaymentRepository extends JpaRepository<Payment,Integer>{
    
    Optional<Payment> findByIdempotentKey(String idempotentKey);
    boolean existsByIdempotentKey(String Idempotentkey);
    Optional<Payment> findByBook_BookingId(Integer bookingId);
    Optional<Payment> findByBook_BookingIdAndStatus(Integer bookingId, PaymentStatus status);
    
}
