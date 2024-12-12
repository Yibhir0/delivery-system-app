package com.devops.backend.controller;

import com.devops.backend.model.Notification;
import com.devops.backend.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api/notification", produces = "application/json")
public class NotificationController{
    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService){
        this.notificationService = notificationService;
    }

    @Tag(name="Company GET Endpoints / Admin GET Endpoints")
    @Operation(summary = "Get all notifications")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Notifications retrieved"),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping(value="/")
    public ResponseEntity<List<Notification>> getAllNotifications(){
        List<Notification> notifications = notificationService.getAll();
        return ResponseEntity.ok(notifications);
    }

    @Tags({
            @Tag(name = "Company GET Endpoints / Admin GET Endpoints"),
            @Tag(name = "User GET Endpoints"),
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Notification retrieved"),
            @ApiResponse(responseCode = "404", description = "Notification not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @Operation(summary = "Get a notification by ID")
    @GetMapping(value="/{notificationId}")
    public ResponseEntity<Notification> getNotificationById(@PathVariable Long notificationId){
        Notification notification = notificationService.get(notificationId);
        return ResponseEntity.ok(notification);
    }

    @Tag(name="User GET Endpoints")
    @Operation(summary = "Get number of unread notifications by user ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Number of unread notifications retrieved"),
            @ApiResponse(responseCode = "404", description = "User ID not valid", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping(value="/user/{userId}/unread")
    public ResponseEntity<Long> getNumberOfUnreadNotificationsByUserId(@PathVariable Long userId){
        Long count = notificationService.getNumberOfUnreadByUserId(userId);
        return ResponseEntity.ok(count);
    }

    @Tag(name = "User GET Endpoints")
    @Operation(summary = "Get all notifications by user ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of notifications retrieved"),
            @ApiResponse(responseCode = "404", description = "User ID not valid", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping(value="/user/{userId}")
    public ResponseEntity<List<Notification>> getAllNotificationsByUserId(@PathVariable Long userId){
        List<Notification> notifications = notificationService.getAllByUserId(userId);
        return ResponseEntity.ok(notifications);
    }

    @Tag(name = "User POST Endpoints")
    @Operation(summary = "Mark a notification as read")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Notification marked as read"),
            @ApiResponse(responseCode = "404", description = "Notification ID not valid", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PostMapping(value="/{notificationId}/read")
    public ResponseEntity<Notification> markNotificationAsRead(@PathVariable Long notificationId){
        Notification notification = notificationService.markRead(notificationId, true);
        return ResponseEntity.ok(notification);
    }

    @Tag(name = "User POST Endpoints")
    @Operation(summary = "Mark a notification as unread")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Notification marked as read"),
            @ApiResponse(responseCode = "404", description = "Notification ID not valid", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PostMapping(value="/{notificationId}/unread")
    public ResponseEntity<Notification> markNotificationAsUnRead(@PathVariable Long notificationId){
        Notification notification = notificationService.markRead(notificationId, false);
        return ResponseEntity.ok(notification);
    }
}