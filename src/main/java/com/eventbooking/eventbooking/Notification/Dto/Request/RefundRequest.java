package com.eventbooking.eventbooking.Notification.Dto.Request;

import java.time.LocalDateTime;
import com.eventbooking.eventbooking.Payments.model.Payment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RefundRequest {
    @NotNull
    private Payment pay;
    @NotBlank
    private Integer userid;
    private LocalDateTime date;

    

    public RefundRequest(Payment pay, Integer userid, LocalDateTime date) {
        this.pay = pay;
        this.userid = userid;
        this.date = date;
    }

    public RefundRequest()
    {

    }

    public void setPayment(Payment pay)
    {
        this.pay = pay;
    }

    public Payment getPayment()
    {
        return pay;
    }

    public void setUserId(Integer userid)
    {
        this.userid = userid;
    }

    public Integer getUserId()
    {
        return userid;
    }

    public LocalDateTime getDate()
    {
        return date;
    }

    public void setDate(LocalDateTime date)
    {
        this.date = date;
    }

}
