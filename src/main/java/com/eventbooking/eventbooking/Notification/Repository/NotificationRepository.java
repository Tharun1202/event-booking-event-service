package com.eventbooking.eventbooking.Notification.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eventbooking.eventbooking.Notification.model.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Integer>{
    
}
