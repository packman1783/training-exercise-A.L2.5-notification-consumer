package com.example.spring_kafka.manualNotification;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request for manual sending of email notification.")
public class ManualNotificationRequest {
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