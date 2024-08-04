package com.ludistudy.notification.Service;

import com.ludistudy.clients.notification.NotificationRequest;
import com.ludistudy.notification.Entity.Notification;
import com.ludistudy.notification.Repository.NotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public void sendNotification(NotificationRequest notificationRequest) {

        Notification notification = Notification.builder()
                .message(notificationRequest.message())
                .sender("ludi study")
                .sentAt(LocalDateTime.now())
                .toCustomerEmail(notificationRequest.toCustomerEmail())
                .toCustomerId(notificationRequest.toCustomerId())
                .build();
        notificationRepository.save(notification);
    }
}
