package project.backend.carts.cartItem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import project.backend.carts.cart.model.Cart;
import project.backend.courses.course.model.Course;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CartItem {
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
    @JsonBackReference
    @ManyToOne
    @JoinColumn(
            name="cart_id",
            nullable=false
    )
    private Cart cart;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}
