package project.backend.carts.cartItem.service;

import org.springframework.stereotype.Component;
import project.backend.carts.cartItem.model.CartItem;
import project.backend.carts.cartItem.dto.CartItemDTO;

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
