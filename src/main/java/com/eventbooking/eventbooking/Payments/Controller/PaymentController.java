package com.eventbooking.eventbooking.Payments.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eventbooking.eventbooking.Event.Dto.Response.ApiResponse;
import com.eventbooking.eventbooking.Payments.Dto.Request.CreatePaymentRequest;
import com.eventbooking.eventbooking.Payments.Dto.Response.PaymentResponse;
import com.eventbooking.eventbooking.Payments.Service.PaymentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    @Autowired
    PaymentService paymentService;
    
    @PostMapping()
    public ResponseEntity<ApiResponse<PaymentResponse>> payment(@Valid @RequestBody CreatePaymentRequest request)
    {
        ApiResponse<PaymentResponse> response = new ApiResponse<>();
        response.setData(paymentService.payment(request));
        response.setMessage("Payment successful!!!");
        response.setStatus("SUCCESS");
        response.setStatusCode(201);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
