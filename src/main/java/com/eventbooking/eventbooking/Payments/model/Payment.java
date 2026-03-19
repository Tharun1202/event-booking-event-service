package com.eventbooking.eventbooking.Payments.model;

import java.time.LocalDateTime;

import com.eventbooking.eventbooking.Booking.models.Booking;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paymentId;
    @ManyToOne
    @JoinColumn(name = "booking_id",nullable = false)
    private Booking book;
    private double amount;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
    private String transactionId;
    @Column(unique = true ,nullable = false)
    private String idempotentKey;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Payment()
    {
        
    }

    
    public Payment(Integer paymentId, Booking book, double amount, PaymentStatus status, String transactionId,
            String idempotentKey, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.paymentId = paymentId;
        this.book = book;
        this.amount = amount;
        this.status = status;
        this.transactionId = transactionId;
        this.idempotentKey = idempotentKey;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    



    public String getIdempotentKey() {
        return idempotentKey;
    }


    public void setIdempotentKey(String idempotentKey) {
        this.idempotentKey = idempotentKey;
    }
    public Integer getPaymentId() {
        return paymentId;
    }
    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }
    public Booking getBook() {
        return book;
    }
    public void setBook(Booking book) {
        this.book = book;
    }
    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    public PaymentStatus getStatus() {
        return status;
    }
    public void setStatus(PaymentStatus status) {
        this.status = status;
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
    public String getTransactionId() {
        return transactionId;
    }
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    
}
