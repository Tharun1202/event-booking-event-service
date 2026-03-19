package com.eventbooking.eventbooking.Payments.Dto.Request;

public class CreatePaymentRequest {
    
    private Integer bookingId;
    private String cardName;
    private long cardNumber;
    private Integer expiryMonth;
    private Integer expiryYear;
    private Integer cvv;
    private String idempotentKey;

    public CreatePaymentRequest()
    {

    }
    

    public CreatePaymentRequest(Integer bookingId, String cardName, long cardNumber, Integer expiryMonth,
            Integer expiryYear, Integer cvv, String idempotentKey) {
        this.bookingId = bookingId;
        this.cardName = cardName;
        this.cardNumber = cardNumber;
        this.expiryMonth = expiryMonth;
        this.expiryYear = expiryYear;
        this.cvv = cvv;
        this.idempotentKey = idempotentKey;
    }

    
    public Integer getBookingId() {
        return bookingId;
    }
    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }
    public String getCardName() {
        return cardName;
    }
    public void setCardName(String cardName) {
        this.cardName = cardName;
    }
    public long getCardNumber() {
        return cardNumber;
    }
    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }
    public Integer getExpiryMonth() {
        return expiryMonth;
    }
    public void setExpiryMonth(Integer expiryMonth) {
        this.expiryMonth = expiryMonth;
    }
    public Integer getExpiryYear() {
        return expiryYear;
    }
    public void setExpiryYear(Integer expiryYear) {
        this.expiryYear = expiryYear;
    }
    public Integer getCvv() {
        return cvv;
    }
    public void setCvv(Integer cvv) {
        this.cvv = cvv;
    }
    public String getidempotentKey() {
        return idempotentKey;
    }
    public void setKey(String idempotentKey) {
        this.idempotentKey = idempotentKey;
    }
    
}
