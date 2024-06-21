package project.backend.courses.notification.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import project.backend.courses.notification.model.Notification;
import project.backend.courses.notification.model.NotificationStatus;

import java.security.Principal;
import java.util.List;
import java.util.Map;

public interface NotificationController {
    ResponseEntity<List<Notification>> getUsersNotifications(Principal principal);
    ResponseEntity<List<Notification>> getUsersNotificationsByStatus(Principal principal, NotificationStatus notificationStatus);
    ResponseEntity<Notification> updateNotificationStatus(@PathVariable("notificationId") Long notificationId, @RequestBody Map<String, String> body);
}
