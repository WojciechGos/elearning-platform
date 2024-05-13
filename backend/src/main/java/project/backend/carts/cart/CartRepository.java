package project.backend.carts.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByUserIdAndCartStatus(Long userId, CartStatus cartStatus);
    List<Cart> findByUserId(Long userId);
    List<Cart> findByUserEmailAndCartStatus(String email, CartStatus cartStatus);

    boolean existsByUserIdAndCartStatus(Long userId, CartStatus cartStatus);
}
