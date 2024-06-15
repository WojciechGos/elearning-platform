package project.backend.users.notification.service;

import project.backend.users.notification.model.Notification;

public interface NotificationService {
    Notification createNotification(Notification notification);
    Notification getNotificationById(Long id);
    Notification deleteNotificationById(Long id);
}
