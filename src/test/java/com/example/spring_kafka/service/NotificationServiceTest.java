package com.example.spring_kafka.service;

import com.example.spring_kafka.event.NotificationEvent;
import com.example.spring_kafka.event.OperationType;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class NotificationServiceTest {

    @Mock
    private EmailService emailService;

    @InjectMocks
    private NotificationService notificationService;

    @Test
    void testUserCreatedEmailBody() {
        NotificationEvent event = new NotificationEvent(OperationType.USER_CREATED, "user@example.com");

        notificationService.processNotification(event);

        verify(emailService).sendEmail(eq("user@example.com"), eq("Notice from Example"),
                contains("created"));
    }
}
