package com.example.spring_kafka.handler;


import com.example.spring_kafka.event.NotificationEvent;
import com.example.spring_kafka.service.EmailService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class NotificationListenerTest {
    @Mock
    private EmailService emailService;

    @InjectMocks
    private NotificationListener listener;

    @Test
    void testUserCreatedSendsEmail() {
        NotificationEvent event = new NotificationEvent("USER_CREATED", "test@example.com");

        listener.onNotification(event);

        verify(emailService).sendEmail(eq("test@example.com"), anyString(), contains("created"));
    }

    @Test
    void testUserDeletedSendsEmail() {
        NotificationEvent event = new NotificationEvent("USER_DELETED", "test@example.com");

        listener.onNotification(event);

        verify(emailService).sendEmail(eq("test@example.com"), anyString(), contains("deleted"));
    }
}
