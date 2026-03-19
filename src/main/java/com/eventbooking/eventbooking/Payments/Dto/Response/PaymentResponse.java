package com.eventbooking.eventbooking.Payments.Dto.Response;

import java.time.LocalDateTime;

import com.eventbooking.eventbooking.Payments.model.PaymentStatus;

public class PaymentResponse {
    private Integer paymentId;
    private Integer bookingId;
    private PaymentStatus status;
    private String transactionId;
    private Double amount;
    private LocalDateTime createdAt;

    public PaymentResponse()
    {
        
    }

    public PaymentResponse(Integer paymentId, Integer bookingId, PaymentStatus status, String transactionId,
            Double amount, LocalDateTime createdAt) {
        this.paymentId = paymentId;
        this.bookingId = bookingId;
        this.status = status;
        this.transactionId = transactionId;
        this.amount = amount;
        this.createdAt = createdAt;
    }
    public Integer getPaymentId() {
        return paymentId;
    }
    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }
    public Integer getBookingId() {
        return bookingId;
    }
    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }
    public PaymentStatus getStatus() {
        return status;
    }
    public void setStatus(PaymentStatus status) {
        this.status = status;
    }
    public String getTransactionId() {
        return transactionId;
    }
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    
}
