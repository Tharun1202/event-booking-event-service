package com.eventbooking.eventbooking.Notification.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service
public class NotificationService {
    @Autowired
    private JavaMailSender mailsender;
    @Value("${spring.mail.username}")
    String fromaddress;

    public void testmail(String toaddress ,String subject,String body)
    {
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
        }
    }
    
}
