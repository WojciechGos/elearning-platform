package project.backend.carts.cartItem.service;

import project.backend.carts.cartItem.dto.CartItemDTO;
import project.backend.carts.cartItem.model.CartItem;
import project.backend.carts.cartItem.request.CartItemRequest;

import java.security.Principal;
import java.util.List;

public interface CartItemService {

    List<CartItem> getAllCartItems();

    CartItem getCartItemById(Long cartItemId);

    CartItemDTO createCartItem(CartItemRequest cartItemRequest, Principal principal);

    CartItem updateCartItem(Long cartItemId, CartItem cartItemDetails);

    void deleteCartItem(Long cartItemId);
}
