package project.backend.carts.cart;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import project.backend.carts.cartItem.CartItem;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
public class Cart {
    @SequenceGenerator(
            name = "cart_sequence",
            sequenceName = "cart_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "cart_sequence"
    )
    private Long id;
    //TODO: add this when the user entity is completed
//    @ManyToOne(
//            optional = false
//    )
//    @JoinColumn(
//            name = "user_id",
//            nullable = false
//    )
//    private User user;
    @OneToMany(
            mappedBy = "cart",
            cascade = CascadeType.ALL
    )
    private List<CartItem> items;
    private BigDecimal totalPrice;
    @Enumerated(EnumType.STRING)
    private CartStatus cartStatus;
}
