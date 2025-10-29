package com.example.spring_kafka.handler;

import com.example.spring_kafka.event.NotificationEvent;

import com.example.spring_kafka.service.NotificationService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationListener {
    private final NotificationService notificationService;

    public NotificationListener(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @KafkaListener(topics = "user.notifications", groupId = "notification-service-group")
    public void onNotification(NotificationEvent event) {
        notificationService.processNotification(event);
    }
}
