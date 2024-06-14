package project.backend.notification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.backend.notification.model.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
