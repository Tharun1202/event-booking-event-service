package com.eventbooking.eventbooking.Payments.Service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.eventbooking.eventbooking.Booking.models.Booking;
import com.eventbooking.eventbooking.Booking.models.BookingStatus;
import com.eventbooking.eventbooking.Booking.repository.BookingRepository;
import com.eventbooking.eventbooking.Notification.Dto.Request.BookingConfirmationRequest;
import com.eventbooking.eventbooking.Notification.Service.NotificationService;
import com.eventbooking.eventbooking.Payments.Dto.Request.CreatePaymentRequest;
import com.eventbooking.eventbooking.Payments.Dto.Response.PaymentResponse;
import com.eventbooking.eventbooking.Payments.Repository.PaymentRepository;
import com.eventbooking.eventbooking.Payments.model.Payment;
import com.eventbooking.eventbooking.Payments.model.PaymentStatus;
import com.eventbooking.eventbooking.Users.repository.UserRepository;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import jakarta.transaction.Transactional;

@Service
public class PaymentService {

    
    @Autowired
    PaymentRepository paymentrepo;
    @Autowired
    BookingRepository bookrepo;
    @Autowired 
    UserRepository userrepo;
    @Autowired
    NotificationService notificationService;
    @Value("${spring.mail.password}")
    String pass;
    @Value("${spring.mail.username}")
    String email;

  
    /*
    what should this Service accomplish?
    it should confirm booking for a event.
    how does it do it?
    it first gets the payment info(card) from user and verifies if it valid then payment info is sent to the payment processor(mock).
    if the payment is successful then payment processor would send a webhook which would confirm the transaction. After reciving this transaction payment
    service should confirm this booking by updating the status in bookings table and adding a record in the payment table. Both of this should occur in a single transaction also i should use a lock so that it would avoid any RACE conditions .
    If the payment has failed then it should be added in the payment table with a failed status or we can ignore it.
    after payment we need to send a notification if payment is successful.

    */
   @Transactional
   public PaymentResponse payment(CreatePaymentRequest request)
   {
    //check if the booking is PENDING and not EXPIRED and should not have a SUCCESSFUL Payment. 

    if(paymentrepo.existsByIdempotentKey(request.getidempotentKey()))
    {
        Payment pay = paymentrepo.findByIdempotentKey(request.getidempotentKey()).orElseThrow();
        PaymentResponse resp = new PaymentResponse();
        resp.setAmount(pay.getAmount());
        resp.setBookingId(pay.getBook().getBookingId());
        resp.setPaymentId(pay.getPaymentId());
        resp.setTransactionId(pay.getTransactionId());
        resp.setStatus(pay.getStatus());
        resp.setCreatedAt(pay.getCreatedAt());
        
        return resp;
    }

    Booking book = bookrepo.findByBookingId(request.getBookingId()).orElseThrow();
    if(book.getStatus()!=BookingStatus.PENDING)
    {
    if(book.getStatus()==BookingStatus.CANCELLED)
    {
     throw new RuntimeException("Event is cancelled so Payment would not be processed");   
    }
    if(book.getStatus()==BookingStatus.EXPIRED)
    {
        throw new RuntimeException("Booking expierd....");
    }
    if(book.getStatus()==BookingStatus.COMPLETED)
    {
        throw new RuntimeException("Already Payed for this booking ...");
    }
    }
    if(paymentrepo.findByBook_BookingIdAndStatus(request.getBookingId(), PaymentStatus.SUCCESS).isPresent())
    {
        throw new IllegalArgumentException("Payment is already completed for this booking....");
    }
     // TODO :  add price table with event id as a foreign key . there can be multiple types of booking option(regular, premier , luxury)
     //for now i'll use fixed price
    double price = book.getQuantity()*100.0;
    
    Payment pay =  new Payment();
    pay.setBook(book);
    pay.setAmount(price);
    pay.setStatus(PaymentStatus.CREATED);
    pay.setTransactionId(null);
    pay.setIdempotentKey(request.getidempotentKey());
    paymentrepo.save(pay);

    String transactionid = paymentSimulation(request,price);
    LocalDateTime time = timestamp();
    if(transactionid == null || transactionid.isEmpty())
    {
        pay.setStatus(PaymentStatus.FAILED);
        pay.setUpdatedAt(time);
    }
    else
    {
    pay.setTransactionId(transactionid);
    pay.setCreatedAt(time);
    pay.setUpdatedAt(time);
    pay.setStatus(PaymentStatus.SUCCESS);
    book.setStatus(BookingStatus.COMPLETED);
    bookrepo.save(book);
    }
    if(pay.getStatus() == PaymentStatus.SUCCESS)
{
    BookingConfirmationRequest req = new BookingConfirmationRequest();
    req.setBooking(book);
    req.setUser(userrepo.findById(book.getUserId()).orElseThrow());

    TransactionSynchronizationManager.registerSynchronization(
        new TransactionSynchronization() {
            @Override
            public void afterCommit() {
                notificationService.sendBookingConfirmation(req);
            }
        });
    }
    PaymentResponse response = new PaymentResponse();
    response.setAmount(price);
    response.setBookingId(request.getBookingId());
    response.setPaymentId(pay.getPaymentId());
    response.setStatus(pay.getStatus());
    response.setTransactionId(transactionid);
    response.setCreatedAt(time);

    //System.out.println("email:"+email);
    //System.out.println("password:"+pass);
    //System.out.println("MAIL HOST: " + System.getProperty("spring.mail.host"));
    
    //notificationService.sendmail("lenzalwin@outlook.com", "Payment Confirmation","<h1>Your Payment is confirmed</h1><p>Payment ID: 123</p>");


    return response;
    

   }


   public String paymentSimulation(CreatePaymentRequest request , Double amount)
   {
        String token = "";
        if(Long.toString(request.getCardNumber()).length()==16)
        {
         if(Integer.toString(request.getCvv()).length()==3)
            {
                token = java.util.UUID.randomUUID().toString();
            }   
        }
        return token;
   }

   public LocalDateTime timestamp()
   {
    return LocalDateTime.now();
   }


}
