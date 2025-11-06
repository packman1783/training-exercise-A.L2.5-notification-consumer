package com.example.spring_kafka.controller;

import com.example.spring_kafka.manualNotification.ManualNotificationRequest;
import com.example.spring_kafka.manualNotification.ManualNotificationResponse;
import com.example.spring_kafka.service.EmailService;

import jakarta.validation.Valid;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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
            summary = "Manually send an email (HATEOAS supported)",
            description = "Allows sending an email notification manually without Kafka.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The email was sent successfully.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ManualNotificationResponse.class)
                            )
                    ),
                    @ApiResponse(responseCode = "400", description = "Invalid request data", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
            }
    )
    @PostMapping("/send")
    public ResponseEntity<EntityModel<ManualNotificationResponse>> sendManualEmail(
            @Valid @RequestBody ManualNotificationRequest request) {

        emailService.sendEmail(request.getEmail(), request.getSubject(), request.getBody());

        ManualNotificationResponse response =
                new ManualNotificationResponse("Email sent to " + request.getEmail());

        EntityModel<ManualNotificationResponse> model = EntityModel.of(response);
        model.add(WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(NotificationController.class).sendManualEmail(request))
                .withSelfRel());

        return ResponseEntity.ok(model);
    }
}
