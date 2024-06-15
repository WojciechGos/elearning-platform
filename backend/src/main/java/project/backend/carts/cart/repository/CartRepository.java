package project.backend.carts.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.backend.carts.cart.model.Cart;
import project.backend.carts.cart.model.CartStatus;
import project.backend.users.user.User;
import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByUserIdAndCartStatus(Long userId, CartStatus cartStatus);
    List<Cart> findByUserId(Long userId);
    List<Cart> findByUserEmailAndCartStatus(String email, CartStatus cartStatus);

    boolean existsByUserIdAndCartStatus(Long userId, CartStatus cartStatus);


    @Query("SELECT c FROM Cart c JOIN c.items ci WHERE c.user.email = :userEmail AND ci.course.id = :courseId")
    Optional<Cart> findByUserEmailAndCourseId(@Param("userEmail") String userEmail, @Param("courseId") Long courseId);

    @Query("SELECT c FROM Cart c JOIN c.items ci WHERE ci.course.id = :courseId AND c.cartStatus = :cartStatus")
    List<Cart> findAllCartsByCourseIdAndStatus(@Param("courseId") Long courseId, @Param("cartStatus") CartStatus cartStatus);
}
