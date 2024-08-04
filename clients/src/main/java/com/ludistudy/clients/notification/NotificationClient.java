package com.ludistudy.clients.notification;

import com.ludistudy.clients.fraud.FraudCheckResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(
        value = "notification",
        path = "api/v1/notification"
)
public interface NotificationClient {
    // INSTEAD OF USING REST TEMPLATE ALL CLIENTS USING THIS INTERFACE to call api
    @PostMapping()
    NotificationResponse sendNotification(@RequestBody NotificationRequest notificationRequest);
}
