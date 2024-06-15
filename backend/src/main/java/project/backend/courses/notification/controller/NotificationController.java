package project.backend.courses.notification.controller;

import project.backend.courses.notification.model.Notification;

import java.util.List;

public interface NotificationController {
    List<Notification> getNotifications();
}
