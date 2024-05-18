package project.backend.carts.cartItem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.backend.carts.cartItem.model.CartItem;
import project.backend.carts.cartItem.dto.CartItemDTO;
import project.backend.carts.cartItem.request.CartItemRequest;
import project.backend.carts.cartItem.service.CartItemService;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/cartItems")
public class CartItemControllerImpl implements CartItemController {

    private final CartItemService cartItemService;

    @Override
    @GetMapping
    public ResponseEntity<List<CartItem>> getAllCartItems() {
        List<CartItem> cartItems = cartItemService.getAllCartItems();
        return ResponseEntity.ok(cartItems);
    }

    @Override
    @GetMapping("{id}")
    public ResponseEntity<CartItem> getCartItemById(@PathVariable("id") Long cartItemId) {
        CartItem cartItem = cartItemService.getCartItemById(cartItemId);
        return ResponseEntity.ok(cartItem);
    }

    @Override
    @PostMapping
    public ResponseEntity<CartItemDTO> createCartItem(Principal principal, @RequestBody CartItemRequest cartItemRequest) {
        CartItemDTO createdCartItem = cartItemService.createCartItem(cartItemRequest, principal);
        return new ResponseEntity<>(createdCartItem, HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<CartItem> updateCartItem(@PathVariable("id") Long cartItemId, @RequestBody CartItem cartItemDetails) {
        CartItem updatedCartItem = cartItemService.updateCartItem(cartItemId, cartItemDetails);
        return ResponseEntity.ok(updatedCartItem);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCartItem(@PathVariable("id") Long cartItemId) {
        cartItemService.deleteCartItem(cartItemId);
        return ResponseEntity.noContent().build();
    }

}
