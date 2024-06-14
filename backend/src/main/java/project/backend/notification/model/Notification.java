package project.backend.notification.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Notification {
    @SequenceGenerator(
            name = "cart_item_sequence",
            sequenceName = "cart_item_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "cart_item_sequence"
    )
    private Long id;
    private String message;
    private NotificationStatus notificationStatus;
}
