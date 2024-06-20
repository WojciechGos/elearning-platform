package project.backend.courses.notification.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.backend.courses.notification.model.Notification;
import project.backend.courses.notification.model.NotificationStatus;
import project.backend.courses.notification.service.NotificationService;

import java.security.Principal;
import java.util.List;

@RequestMapping("api/v1/notifications")
@RequiredArgsConstructor
@RestController
public class NotificationControllerImpl implements NotificationController {


    private final NotificationService notificationService;

    @Override
    @GetMapping("/me")
    public ResponseEntity<List<Notification>> getUsersNotifications(Principal principal) {
        return ResponseEntity.ok(notificationService.getUsersNotifications(principal));
    }

    @Override
    @GetMapping("/{notificationStatus}/me")
    public ResponseEntity<List<Notification>> getUsersNotificationsByStatus(Principal principal, @PathVariable("notificationStatus") NotificationStatus notificationStatus) {
        return ResponseEntity.ok(notificationService.getUsersNotificationsByStatus(principal, notificationStatus));
    }

    @Override
    public ResponseEntity<Notification> updateNotificationStatus(Long notificationId, NotificationStatus notificationStatus) {
        return ResponseEntity.ok(notificationService.updateNotificationStatus(notificationId, notificationStatus));
    }
}
