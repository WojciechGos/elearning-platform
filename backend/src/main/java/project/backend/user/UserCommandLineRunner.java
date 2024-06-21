package project.backend.user;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import project.backend.courses.notification.model.Notification;
import project.backend.courses.notification.model.NotificationStatus;
import project.backend.courses.notification.repository.NotificationRepository;

import java.util.List;

@RequiredArgsConstructor
@Component
@Order(17)
public class UserCommandLineRunner implements CommandLineRunner {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final NotificationRepository notificationRepository;
    @Override
    public void run(String... args) throws Exception {

        Notification notification1 = Notification.builder()
                .message("Welcome to the platform!")
                .notificationStatus(NotificationStatus.UNREAD)
                .build();
        notification1 = notificationRepository.save(notification1);
        Notification notification2 = Notification.builder()
                .message("You have a new notification!")
                .notificationStatus(NotificationStatus.READ)
                .build();
        notification2 = notificationRepository.save(notification2);
        userRepository.save(User.builder()
                .email("jan.kowalski@gmail.com")
                .password(passwordEncoder.encode("password1"))
                .firstName("Jan")
                .lastName("Kowalski")
                .role(Role.USER)
                .notificationList(List.of(notification1, notification2))
                .build());

        userRepository.save(User.builder()
                .email("michal.wojcik@gmail.com")
                .password(passwordEncoder.encode("adminpassword"))
                .firstName("Michał")
                .lastName("Wojcik")
                .role(Role.ADMIN)
                .build());

        userRepository.save(User.builder()
                .email("marek.kalwat@gmail.com")
                .password(passwordEncoder.encode("password1"))
                .firstName("Marek")
                .lastName("Kalwat")
                .role(Role.USER)
                .build());

        userRepository.save(User.builder()
                .email("kamil.slimak@gmail.com")
                .password(passwordEncoder.encode("password1"))
                .firstName("Kamil")
                .lastName("slimak")
                .role(Role.USER)
                .build());

        userRepository.save(User.builder()
                .email("piotr.swiezak@gmail.com")
                .password(passwordEncoder.encode("password1"))
                .firstName("Piotr")
                .lastName("swierzak")
                .role(Role.USER)
                .build());
    }
}