package project.backend.carts.cartItem;

import org.springframework.stereotype.Component;

@Component
public class CartItemMapper {
    public CartItemDTO mapToDTO(CartItem cartItem) {
        return new CartItemDTO(
                cartItem.getId(),
                cartItem.getCart().getId(),
                cartItem.getCourse().getId()
        );
    }
}
