package com.example.spring_kafka.controller;

import com.example.spring_kafka.service.EmailService;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NotificationController.class)
class NotificationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EmailService emailService;

    @Test
    void testManualEmailSendEndpoint() throws Exception {
        String json = """
                {
                  "email": "test@example.com",
                  "subject": "Hi",
                  "body": "This is a test"
                }
                """;

        mockMvc.perform(post("/notification/send")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());

        verify(emailService).sendEmail("test@example.com", "Hi", "This is a test");
    }
}

