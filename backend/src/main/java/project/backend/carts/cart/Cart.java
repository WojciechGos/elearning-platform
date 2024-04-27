package project.backend.carts.cart;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import project.backend.carts.cartItem.CartItem;
import project.backend.user.User;

import java.math.BigDecimal;
import java.util.List;

//TODO: add valid annotations?
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
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;
    @JsonManagedReference
    @OneToMany(
            mappedBy = "cart",
            cascade = CascadeType.ALL
    )
    private List<CartItem> items;
    private BigDecimal totalPrice;
    @Enumerated(EnumType.STRING)
    private CartStatus cartStatus;
}
