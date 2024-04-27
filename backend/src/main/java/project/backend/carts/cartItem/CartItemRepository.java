package project.backend.carts.cartItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.backend.carts.cart.Cart;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}

