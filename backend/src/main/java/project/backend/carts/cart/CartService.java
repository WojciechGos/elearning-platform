package project.backend.carts.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.backend.exception.ResourceNotFoundException;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CartService {
    private final CartRepository cartRepository;
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
}
