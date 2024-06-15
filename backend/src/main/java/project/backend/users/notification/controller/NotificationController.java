package project.backend.users.notification.controller;

import project.backend.users.notification.model.Notification;

import java.util.List;

public interface NotificationController {
    List<Notification> getNotifications();
}
