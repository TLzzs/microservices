package com.ludistudy.notification.Controller;


import com.ludistudy.clients.notification.NotificationRequest;
import com.ludistudy.clients.notification.NotificationResponse;
import com.ludistudy.notification.Service.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("api/v1/notification")
public class NotificationController {
    private final NotificationService notificationService;
    @PostMapping
    public ResponseEntity<NotificationResponse>  sendNotification(
            @RequestBody NotificationRequest notificationRequest) {
        log.info("New notification... {}", notificationRequest);
        try {
            notificationService.sendNotification(notificationRequest);
            log.info("Notification sent successfully");
            return ResponseEntity.ok(new NotificationResponse("Notification sent successfully"));
        } catch (Exception e) {
            log.error("Error sending notification", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new NotificationResponse(e.getMessage()));
        }
    }
}
