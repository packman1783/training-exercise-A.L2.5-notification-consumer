package com.example.spring_kafka.handler;

import com.example.spring_kafka.event.NotificationEvent;
import com.example.spring_kafka.event.OperationType;
import com.example.spring_kafka.service.EmailService;
import com.example.spring_kafka.service.NotificationService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class NotificationListenerTest {

    @Mock
    private EmailService emailService;

    @InjectMocks
    private NotificationService notificationService;

    @Test
    void testUserCreatedEmail() {
        NotificationEvent event = new NotificationEvent(OperationType.USER_CREATED, "test@example.com");

        notificationService.processNotification(event);

        verify(emailService).sendEmail(eq("test@example.com"), eq("Notice from Example"), contains("created"));
    }

    @Test
    void testUserDeletedEmail() {
        NotificationEvent event = new NotificationEvent(OperationType.USER_DELETED, "test@example.com");

        notificationService.processNotification(event);

        verify(emailService).sendEmail(eq("test@example.com"), eq("Notice from Example"), contains("deleted"));
    }
}
