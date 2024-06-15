package project.backend.carts.cart.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import project.backend.auditing.AuditorEntity;
import project.backend.carts.cartItem.model.CartItem;
import project.backend.users.user.User;

import java.math.BigDecimal;
import java.util.List;

//TODO: add valid annotations?
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cart extends AuditorEntity {
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
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference(value = "user-cart")
    private User user;
    @JsonManagedReference(value = "cart-items")
    @OneToMany(
            mappedBy = "cart",
            cascade = CascadeType.ALL
    )
    private List<CartItem> items;
    private BigDecimal totalPrice;
    @Enumerated(EnumType.STRING)
    private CartStatus cartStatus;
}
