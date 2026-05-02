package com.eventbooking.eventbooking.Notification.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "notification",
    uniqueConstraints = @UniqueConstraint(
    columnNames = {"bookingId", "type"})
)
public class Notification {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer notificationId;
    private Integer userId;
    private Integer bookingId;
    @Enumerated(EnumType.STRING)
    private NotificationType type;
    @Enumerated(EnumType.STRING)
    private NotificationStatus status;
    private LocalDateTime time;

    public Notification()
    {
        
    }

    public Notification(Integer notificationId, Integer userId, Integer bookingId, NotificationType type,
            NotificationStatus status, LocalDateTime time) {
        this.notificationId = notificationId;
        this.userId = userId;
        this.bookingId = bookingId;
        this.type = type;
        this.status = status;
        this.time = time;
    }

    public Integer getNotificationId() {
        return notificationId;
    }
    public void setNotificationId(Integer notificationId) {
        this.notificationId = notificationId;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getBookingId() {
        return bookingId;
    }
    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }
    public NotificationType getType() {
        return type;
    }
    public void setType(NotificationType type) {
        this.type = type;
    }
    public NotificationStatus getStatus() {
        return status;
    }
    public void setStatus(NotificationStatus status) {
        this.status = status;
    }
    public LocalDateTime getTime() {
        return time;
    }
    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    



}
