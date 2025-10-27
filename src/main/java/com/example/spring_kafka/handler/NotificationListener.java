package com.example.spring_kafka.handler;

import com.example.spring_kafka.event.NotificationEvent;
import com.example.spring_kafka.service.EmailService;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static com.example.spring_kafka.event.OperationType.USER_CREATED;
import static com.example.spring_kafka.event.OperationType.USER_DELETED;
import static com.example.spring_kafka.event.OperationType.USER_UPDATED;

@Service
public class NotificationListener {
    private final EmailService emailService;

    public NotificationListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @KafkaListener(topics = "user.notifications", groupId = "notification-service-group")
    public void onNotification(NotificationEvent event) {
        if (event == null || event.getEmail() == null || event.getOperation() == null) {
            return;
        }

        String subject = "Notice from Example";
        String body;

        switch (event.getOperation()) {
            case USER_CREATED -> body = "Hello! Your account has been successfully created!!!";
            case USER_DELETED -> body = "Your account has been deleted!!!";
            case USER_UPDATED -> body = "Your account data has been updated!";
            default -> body = "Event occurred: " + event.getOperation();
        }

        emailService.sendEmail(event.getEmail(), subject, body);
    }
}
