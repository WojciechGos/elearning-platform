package project.backend.carts.cart.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import project.backend.carts.cart.model.Cart;
import project.backend.carts.cart.model.CartStatus;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface CartService {

    List<Cart> getAllCarts();

    Cart getCartById(Long cartId);

    Cart createCart(Cart cart);

    Cart updateCart(Long cartId, Cart cartDetails);

    void deleteCart(Long cartId);

    Optional<Cart> getOptionalPendingCartByUserEmail(String email);

    Cart getPendingCart(String email);

    List<Cart> getAllCartsByUser(String email);

    List<Cart> getAllPendingCartsByUser(String email);

    List<Cart> getUsersCartsByStatus(CartStatus cartStatus, Principal principal);

    boolean hasBoughtCourse(@PathVariable("courseId") Long courseId, Principal principal);

    List<Cart> getAllCartsByCourseIdAndCartStatus(Long courseId, CartStatus cartStatus);
}

