package com.example.spring_kafka.controller;

import com.example.spring_kafka.service.EmailService;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/notification")
@Tag(name = "Notification", description = "API for sending email notifications")
public class NotificationController {
    private final EmailService emailService;

    public NotificationController(EmailService emailService) {
        this.emailService = emailService;
    }

    @Operation(
            summary = "Sending an email manually",
            description = "Allows you to manually send an email notification without using Kafka.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The letter was sent successfully.",
                            content = @Content(
                                    mediaType = "text/plain",
                                    schema = @Schema(example = "Email sent to test@mail.com")
                            )
                    )
            }
    )
    @PostMapping("/send")
    public ResponseEntity<String> sendManualEmail(@RequestBody ManualNotificationRequest request) {
        emailService.sendEmail(request.getEmail(), request.getSubject(), request.getBody());

        return ResponseEntity.ok("Email sent to " + request.getEmail());
    }

    @Schema(description = "Request for manual sending of email notification")
    static class ManualNotificationRequest {
        @Schema(description = "Recipient's email")
        private String email;
        @Schema(description = "Subject of the letter")
        private String subject;
        @Schema(description = "Letter text")
        private String body;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }
    }
}
