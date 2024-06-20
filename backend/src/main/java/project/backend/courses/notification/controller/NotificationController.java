package project.backend.courses.notification.controller;

import org.springframework.http.ResponseEntity;
import project.backend.courses.notification.model.Notification;
import project.backend.courses.notification.model.NotificationStatus;

import java.security.Principal;
import java.util.List;

public interface NotificationController {
    ResponseEntity<List<Notification>> getUsersNotifications(Principal principal);
    ResponseEntity<List<Notification>> getUsersNotificationsByStatus(Principal principal, NotificationStatus notificationStatus);
    ResponseEntity<Notification> updateNotificationStatus(Long notificationId, NotificationStatus notificationStatus);
}
