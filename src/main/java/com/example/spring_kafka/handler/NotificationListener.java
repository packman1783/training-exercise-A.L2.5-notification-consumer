package com.example.spring_kafka.handler;

import com.example.spring_kafka.event.NotificationEvent;
import com.example.spring_kafka.service.EmailService;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationListener {
    private final EmailService emailService;

    public NotificationListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @KafkaListener(topics = "user.notifications", groupId = "notification-service-group")
    public void onNotification(NotificationEvent event) {
        if (event == null || event.getEmail() == null) {
            return;
        }

        String subject = "Notice from Example";
        String body;

        if ("USER_CREATED".equals(event.getOperation())) {
            body = "Hello! Your account has been successfully created!!!";
        } else if ("USER_DELETED".equals(event.getOperation())) {
            body = "Your account has been deleted!!!";
        } else {
            body = "Event occurred: " + event.getOperation();
        }

        emailService.sendEmail(event.getEmail(), subject, body);
    }
}
