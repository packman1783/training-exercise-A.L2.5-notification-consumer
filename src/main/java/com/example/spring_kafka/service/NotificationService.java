package com.example.spring_kafka.service;

import com.example.spring_kafka.event.NotificationEvent;
import com.example.spring_kafka.event.OperationType;

import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private final EmailService emailService;

    public NotificationService(EmailService emailService) {
        this.emailService = emailService;
    }

    public void processNotification(NotificationEvent event) {
        if (event == null || event.getEmail() == null || event.getOperation() == null) {
            return;
        }

        String subject = "Notice from Example";
        String body = generateMessageBody(event.getOperation());

        emailService.sendEmail(event.getEmail(), subject, body);
    }

    private String generateMessageBody(OperationType operation) {
        return switch (operation) {
            case USER_CREATED -> "Hello! Your account has been successfully created!!!";
            case USER_DELETED -> "Your account has been deleted!!!";
            case USER_UPDATED -> "Your account data has been updated!!!";
        };
    }
}
