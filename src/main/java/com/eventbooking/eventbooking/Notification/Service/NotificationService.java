package com.eventbooking.eventbooking.Notification.Service;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import com.eventbooking.eventbooking.Notification.Dto.Request.BookingConfirmationRequest;
import com.eventbooking.eventbooking.Notification.Repository.NotificationRepository;
import com.eventbooking.eventbooking.Notification.model.Notification;
import com.eventbooking.eventbooking.Notification.model.NotificationStatus;
import com.eventbooking.eventbooking.Notification.model.NotificationType;
import com.eventbooking.eventbooking.Users.model.User;
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
        n1.setBookingId(request.getBooking().getBookingId());
        n1.setUserId(user.getUserId());
        n1.setType(NotificationType.BOOKING_CONFIRMED);
        n1.setStatus(NotificationStatus.PENDING);
        n1.setTime(timestamp());
        try {
            n1 = notificationrepo.save(n1);  // insert attempt
        } catch (Exception e) {
        e.printStackTrace();
        return;
        }

        boolean email = sendmail(user.getEmail(), "dummy text", "dummy body");
        if(email==true)
        {
            n1.setStatus(NotificationStatus.SENT);
        }
        else
        {
            n1.setStatus(NotificationStatus.RETRY_1);
        }
        notificationrepo.save(n1);
    }
    
    public LocalDateTime timestamp()
    {
        return LocalDateTime.now();
    }

}
