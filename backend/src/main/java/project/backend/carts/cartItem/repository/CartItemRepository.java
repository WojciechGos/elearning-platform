package project.backend.carts.cartItem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.backend.carts.cartItem.model.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}

