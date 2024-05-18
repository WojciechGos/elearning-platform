package project.backend.carts.cart.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.backend.carts.cart.model.Cart;

import java.security.Principal;
import java.util.List;

public interface CartController {
    @GetMapping
    ResponseEntity<List<Cart>> getAllCarts();

    @GetMapping("{id}")
    ResponseEntity<Cart> getCartById(@PathVariable("id") Long cartId);

    @GetMapping("pending")
    ResponseEntity<Cart> getPendingCart(Principal principal);

    @GetMapping("user/{email}")
    ResponseEntity<List<Cart>> getAllCartsByUser(@PathVariable("email") String email);

    @GetMapping("user/{email}/pending")
    ResponseEntity<List<Cart>> getAllPendingCartsByUser(@PathVariable("email") String email);

    @PostMapping
    ResponseEntity<Cart> createCart(@RequestBody Cart cart);

    @PutMapping("{id}")
    ResponseEntity<Cart> updateCart(
            @PathVariable("id") Long cartId,
            @RequestBody Cart cartDetails
    );

    @DeleteMapping("{id}")
    ResponseEntity<Void> deleteCart(@PathVariable("id") Long cartId);
}
