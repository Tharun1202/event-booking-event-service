package com.eventbooking.eventbooking.Notification.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import com.eventbooking.eventbooking.Notification.model.NotificationType;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class BuildTemplate {
    
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private TemplateEngine engine;


    public String generateEmail(NotificationType type, Object dto)
    {
      String templateName = TEMPLATE_MAP.get(type);
      if (templateName == null) {
        throw new IllegalArgumentException("No template found for type: " + type);
      }
      Map<String, Object> variables = objectMapper.convertValue(dto, Map.class);
      if (variables.containsKey("eventDate")) {
            Object date = variables.get("eventDate");
              if (date instanceof LocalDateTime ldt) {
                    DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a");
                    variables.put("eventDate", ldt.format(formatter));
              }
      }
      Context context = new Context();
      context.setVariables(variables);
      return engine.process(templateName ,context);
    }

    private static final Map<NotificationType, String> TEMPLATE_MAP = Map.of(
    NotificationType.BOOKING_CONFIRMED, "booking-confirmed",
    NotificationType.BOOKING_CANCELLATION, "booking-cancelled",
    NotificationType.PAYMENT_SUCCESS, "payment-success",
    NotificationType.REFUND, "refund-initiation"
);
}
