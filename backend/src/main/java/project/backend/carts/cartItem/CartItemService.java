package project.backend.carts.cartItem;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.backend.exception.ResourceNotFoundException;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CartItemService {
    private final CartItemRepository cartItemRepository;

    public List<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }

    public CartItem getCartItemById(Long cartItemId) {
        return cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("CartItem with id [%s] not found.", cartItemId)
                ));
    }

    public CartItem createCartItem(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    public CartItem updateCartItem(Long cartItemId, CartItem cartItemDetails) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("CartItem with id [%s] not found.", cartItemId)
                ));

        cartItem.setCart(cartItemDetails.getCart());
        cartItem.setPrice(cartItemDetails.getPrice());

        return cartItemRepository.save(cartItem);
    }

    public void deleteCartItem(Long cartItemId) {
        if (!cartItemRepository.existsById(cartItemId)) {
            throw new ResourceNotFoundException(
                    String.format("CartItem with id [%s] not found.", cartItemId)
            );
        }
        cartItemRepository.deleteById(cartItemId);
    }
}

