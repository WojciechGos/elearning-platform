package project.backend.notification.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.backend.notification.model.Notification;
import project.backend.notification.repository.NotificationRepository;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService{

    private final NotificationRepository notificationRepository;


    @Override
    public Notification createNotification(Notification notification) {
        return null;
    }

    @Override
    public Notification getNotificationById(Long id) {
        return null;
    }

    @Override
    public Notification deleteNotificationById(Long id) {
        return null;
    }
}
