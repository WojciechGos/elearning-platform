package project.backend.notification.controller;

import project.backend.notification.model.Notification;

import java.util.List;

public interface NotificationController {
    List<Notification> getNotifications();
}
