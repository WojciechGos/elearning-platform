package project.backend.carts.cartItem;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.backend.carts.cart.Cart;
import project.backend.courses.course.Course;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
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
    public CartItem(
            Cart cart,
            Course course
    ) {
        this.cart = cart;
        this.course = course;
    }
}
