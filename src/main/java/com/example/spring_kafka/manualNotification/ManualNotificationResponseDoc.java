package com.example.spring_kafka.manualNotification;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

@Schema(description = "Manual notification response with HATEOAS links (for Swagger)")
public class ManualNotificationResponseDoc {

    @Schema(description = "Status message of the email operation", example = "Email sent to test@mail.com")
    private String message;

    @Schema(description = "HATEOAS links for navigation", example = "{\"self\":\"/notification/send\",\"help\":\"/notification/help\"}")
    private Map<String, String> _links;

    public ManualNotificationResponseDoc() {}

    public ManualNotificationResponseDoc(String message, Map<String, String> _links) {
        this.message = message;
        this._links = _links;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, String> get_links() {
        return _links;
    }

    public void set_links(Map<String, String> _links) {
        this._links = _links;
    }
}