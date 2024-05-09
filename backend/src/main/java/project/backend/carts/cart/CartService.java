package project.backend.carts.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.backend.exception.ResourceNotFoundException;
import project.backend.user.User;
import project.backend.user.UserService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CartService {
    private final CartRepository cartRepository;
    private final UserService userService;
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    public Cart getCartById(Long cartId) {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Cart with id [%s] not found.".formatted(cartId)
                ));
    }

    public Cart createCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public Cart updateCart(Long cartId, Cart cartDetails) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Cart with id [%s] not found.", cartId)
                ));

        cart.setItems(cartDetails.getItems());
        cart.setTotalPrice(cartDetails.getTotalPrice());
        cart.setCartStatus(cartDetails.getCartStatus());

        return cartRepository.save(cart);
    }

    public void deleteCart(Long cartId) {
        if (!cartRepository.existsById(cartId)) {
            throw new ResourceNotFoundException(
                    String.format("Cart with id [%s] not found.", cartId)
            );
        }
        cartRepository.deleteById(cartId);
    }

    public Optional<Cart> getOptionalPendingCartByUserEmail(String email) {
        User user = userService.getUserByEmail(email);

        List<Cart> carts = cartRepository.findByUserIdAndCartStatus(user.getId(), CartStatus.PENDING);
        if (carts.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(carts.get(0));
    }

    public Cart getPendingCartByUserEmail(String email) {
        User user = userService.getUserByEmail(email);
        List<Cart> carts = cartRepository.findByUserIdAndCartStatus(user.getId(), CartStatus.PENDING);

        if (carts.isEmpty()) {
            throw new ResourceNotFoundException(
                    String.format("Pending cart for user with email [%s] not found.", email)
            );
        }

        return carts.get(0);
    }

    public List<Cart> getAllCartsByUserEmail(String email) {
        User user = userService.getUserByEmail(email);

        return cartRepository.findByUserId(user.getId());
    }
}
