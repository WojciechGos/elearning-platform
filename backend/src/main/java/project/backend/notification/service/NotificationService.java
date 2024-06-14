package project.backend.notification.service;

import project.backend.notification.model.Notification;

public interface NotificationService {
    Notification createNotification(Notification notification);
    Notification getNotificationById(Long id);
    Notification deleteNotificationById(Long id);
}
