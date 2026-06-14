package com.eventbooking.eventbooking.Notification.Service;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import com.eventbooking.eventbooking.Booking.models.Booking;
import com.eventbooking.eventbooking.Notification.Dto.Request.BookingConfirmationRequest;
import com.eventbooking.eventbooking.Notification.Dto.Request.BookingConfirmedEmailData;
import com.eventbooking.eventbooking.Notification.Dto.Request.RefundEmailData;
import com.eventbooking.eventbooking.Notification.Dto.Request.RefundRequest;
import com.eventbooking.eventbooking.Notification.Repository.NotificationRepository;
import com.eventbooking.eventbooking.Notification.model.Notification;
import com.eventbooking.eventbooking.Notification.model.NotificationStatus;
import com.eventbooking.eventbooking.Notification.model.NotificationType;
import com.eventbooking.eventbooking.Notification.util.BuildTemplate;
import com.eventbooking.eventbooking.Payments.model.Payment;
import com.eventbooking.eventbooking.Users.model.User;
import com.eventbooking.eventbooking.Users.repository.UserRepository;

import jakarta.mail.internet.MimeMessage;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NotificationService {
    @Autowired
    private JavaMailSender mailsender;
    @Value("${spring.mail.username}")
    String fromaddress;
    @Autowired
    NotificationRepository notificationrepo;
    @Autowired
    BuildTemplate template;
    @Autowired
    UserRepository userrepo;

    public boolean sendmail(String toaddress ,String subject,String body)
    {   
        boolean isSent=false;
        try 
        {
            MimeMessage message = mailsender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            helper.setFrom(fromaddress);
            helper.setTo(toaddress);
            helper.setSubject(subject);
            helper.setText(body,true);
            mailsender.send(message);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
        isSent = true;
        return isSent;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void sendBookingConfirmation(BookingConfirmationRequest request)
    {
        Notification n1 = new Notification();
        User user = request.getUser();
        Booking book =request.getBooking();
        n1.setBookingId(request.getBooking().getBookingId());
        n1.setUserId(user.getUserId());
        n1.setType(NotificationType.BOOKING_CONFIRMED);
        n1.setStatus(NotificationStatus.PENDING);
        n1.setTime(timestamp());
        notificationrepo.save(n1);
        

        BookingConfirmedEmailData data =  new BookingConfirmedEmailData();
        data.setBookingId(book.getBookingId());
        data.setUsername(user.getUsername());
        data.setEventName(book.getEvent().getEventName());
        data.setSeatCount(book.getQuantity());
        data.setEventDate(book.getEvent().getStartDateTime().toLocalDate());

        String body  = template.generateEmail(NotificationType.BOOKING_CONFIRMED, data);
        
        boolean email = sendmail(user.getEmail(), "Booking Confirmation", body);
        if(email==true)
        {
            n1.setStatus(NotificationStatus.SENT);
        }
        else
        {
            n1.setStatus(NotificationStatus.RETRY_1);  // retry logic will be handled by a scheduler ( to be implemented)
        }
        notificationrepo.save(n1);
    }

    public void sendRefund(RefundRequest request)
    {
        if(request.getPayment()==null)
        {
            throw new IllegalArgumentException("Payment not found!!!");
        }
        if(request.getUserId()==null)
        {
            throw new IllegalArgumentException("UserID is null...");
        }
        if(request.getDate()==null)
        {
            request.setDate(timestamp());
        }

        Payment pay = request.getPayment();
        User user = userrepo.findById(request.getUserId()).orElseThrow();

        Notification n = new Notification();
        n.setBookingId(request.getPayment().getBook().getBookingId());
        n.setUserId(request.getUserId());
        n.setTime(timestamp());
        n.setType(NotificationType.REFUND);
        n.setStatus(NotificationStatus.PENDING);
        notificationrepo.save(n);
        
        RefundEmailData data = new RefundEmailData();

        data.setPaymentId(pay.getPaymentId());
        data.setBookingId(pay.getBook().getBookingId());
        data.setAmount(pay.getAmount());
        data.seteventName(pay.getBook().getEvent().getEventName());
        data.setUsername(user.getUsername());
        data.setDate(LocalDateTime.now());
        String body = template.generateEmail(NotificationType.REFUND, data);
        boolean mail = sendmail(user.getEmail(),"Refund Initiated", body);
        if(mail==true)
        {
            n.setStatus(NotificationStatus.SENT);
        }
        else
        {
            n.setStatus(NotificationStatus.RETRY_1);  
        }
        notificationrepo.save(n);

    }

    
    public LocalDateTime timestamp()
    {
        return LocalDateTime.now();
    }

}
