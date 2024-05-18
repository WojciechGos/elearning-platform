package project.backend.carts.cartItem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.backend.carts.cartItem.model.CartItem;
import project.backend.carts.cartItem.dto.CartItemDTO;
import project.backend.carts.cartItem.request.CartItemRequest;

import java.security.Principal;
import java.util.List;

public interface CartItemController {

    @GetMapping
    ResponseEntity<List<CartItem>> getAllCartItems();

    @GetMapping("{id}")
    ResponseEntity<CartItem> getCartItemById(@PathVariable("id") Long cartItemId);

    @PostMapping
    ResponseEntity<CartItemDTO> createCartItem(Principal principal, @RequestBody CartItemRequest cartItemRequest);

    @PutMapping("/{id}")
    ResponseEntity<CartItem> updateCartItem(@PathVariable("id") Long cartItemId, @RequestBody CartItem cartItemDetails);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteCartItem(@PathVariable("id") Long cartItemId);
}
