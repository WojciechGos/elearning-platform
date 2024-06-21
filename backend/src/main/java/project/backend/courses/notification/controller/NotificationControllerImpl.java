package project.backend.courses.notification.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.backend.courses.notification.model.Notification;
import project.backend.courses.notification.model.NotificationStatus;
import project.backend.courses.notification.service.NotificationService;

import java.security.Principal;
import java.util.List;
import java.util.Map;

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
    @PutMapping("/{notificationId}")
    public ResponseEntity<Notification> updateNotificationStatus(@PathVariable("notificationId") Long notificationId, @RequestBody Map<String, String> body) {
        NotificationStatus notificationStatus = NotificationStatus.valueOf(body.get("notificationStatus"));
        return ResponseEntity.ok(notificationService.updateNotificationStatus(notificationId, notificationStatus));
    }
}
