package project.backend.courses.notification.service;

import project.backend.courses.notification.model.Notification;
import project.backend.courses.notification.model.NotificationStatus;

import java.security.Principal;
import java.util.List;

public interface NotificationService {
    void assignNotifications(String message, Long courseId);
    Notification createNotification(Notification notification);
    Notification getNotificationById(Long id);
    Notification updateNotificationStatus(Long notificationId, NotificationStatus notificationStatus);
    Notification deleteNotificationById(Long id);
    List<Notification> getUsersNotifications(Principal principal);
    List<Notification> getUsersNotificationsByStatus(Principal principal, NotificationStatus notificationStatus);
}
