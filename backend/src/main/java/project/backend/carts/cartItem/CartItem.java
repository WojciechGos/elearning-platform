package project.backend.carts.cartItem;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import project.backend.carts.cart.Cart;

import java.math.BigDecimal;

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
    @ManyToOne
    @JoinColumn(
            name="cart_id",
            nullable=false
    )
    private Cart cart;
    private BigDecimal price;
}
