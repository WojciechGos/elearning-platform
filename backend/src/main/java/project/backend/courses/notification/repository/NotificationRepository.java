package project.backend.courses.notification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.backend.courses.notification.model.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
