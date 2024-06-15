package project.backend.courses.notification.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.backend.carts.cart.model.Cart;
import project.backend.carts.cart.model.CartStatus;
import project.backend.carts.cart.service.CartService;
import project.backend.courses.notification.model.Notification;
import project.backend.courses.notification.model.NotificationStatus;
import project.backend.courses.notification.repository.NotificationRepository;
import project.backend.users.user.User;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final CartService cartService;

    @Override
    public void broadcastNotification(String message, Long courseId) {

        Notification notification = createNotification(
                Notification.builder()
                        .message(message)
                        .notificationStatus(NotificationStatus.UNREAD)
                        .build()
        );


        List<Cart> cartList = cartService.getAllCartsByCourseIdAndCartStatus(courseId, CartStatus.COMPLETED);
        cartList.stream().map(Cart::getUser).forEach(user -> {
            List<Notification> notificationList = user.getNotificationList();
            notificationList.add(notification);
            // TODO save notification to user
        });
    }

    @Override
    public Notification createNotification(Notification notification) {
        return notificationRepository.save(notification);
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
