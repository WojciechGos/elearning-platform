package project.backend.courses.notification.service;

import project.backend.courses.notification.model.Notification;

public interface NotificationService {
    void broadcastNotification(String message, Long courseId);
    Notification createNotification(Notification notification);
    Notification getNotificationById(Long id);
    Notification deleteNotificationById(Long id);
}
