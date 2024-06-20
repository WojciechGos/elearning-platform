package project.backend.courses.notification.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.backend.carts.cart.model.Cart;
import project.backend.carts.cart.model.CartStatus;
import project.backend.carts.cart.service.CartService;
import project.backend.courses.notification.model.Notification;
import project.backend.courses.notification.model.NotificationStatus;
import project.backend.courses.notification.repository.NotificationRepository;
import project.backend.exception.types.ResourceNotFoundException;
import project.backend.user.User;
import project.backend.user.UserService;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final CartService cartService;
    private final UserService userService;
    @Override
    public void assignNotifications(String message, Long courseId) {

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
            user.setNotificationList(notificationList);
            userService.saveUser(user);
        });
    }

    @Override
    public Notification createNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public Notification getNotificationById(Long id) {
        return notificationRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Notification with id [" + id + "] not found."));
    }

    @Override
    public Notification updateNotificationStatus(Long notificationId, NotificationStatus notificationStatus) {
        Notification notification = getNotificationById(notificationId);
        notification.setNotificationStatus(notificationStatus);
        return notificationRepository.save(notification);
    }

    @Override
    public Notification deleteNotificationById(Long id) {
        return null;
    }

    @Override
    public List<Notification> getUsersNotifications(Principal principal) {
        if(principal == null)
            throw new ResourceNotFoundException("User not found");
        User user = userService.getUserByEmail(principal.getName());
        return user.getNotificationList();
    }

    @Override
    public List<Notification> getUsersNotificationsByStatus(Principal principal, NotificationStatus notificationStatus) {
        if(principal == null)
            throw new ResourceNotFoundException("User not found");
        return userService.getUsersNotificationsByStatus(principal, notificationStatus);
    }
}
