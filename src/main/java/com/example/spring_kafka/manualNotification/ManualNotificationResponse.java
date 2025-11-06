package com.example.spring_kafka.manualNotification;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Response with HATEOAS links")
public class ManualNotificationResponse {
    @Schema(description = "Status message", example = "Email sent to user@mail.com")
    private String message;

    public ManualNotificationResponse() {}

    public ManualNotificationResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
