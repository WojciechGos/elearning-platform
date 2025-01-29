package project.backend.carts.cart.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.backend.carts.cart.model.Cart;
import project.backend.carts.cart.model.CartStatus;
import project.backend.carts.cart.request.CartPutRequest;
import project.backend.carts.cart.service.CartService;

import java.security.Principal;
import java.util.List;

@RequestMapping("api/v1/carts")
@RequiredArgsConstructor
@RestController
public class CartControllerImpl implements CartController {
    private final CartService cartService;

    @Override
    @GetMapping
    public ResponseEntity<List<Cart>> getAllCarts() {
        List<Cart> carts = cartService.getAllCarts();
        return ResponseEntity.ok(carts);
    }

    @Override
    @GetMapping("{id}")
    public ResponseEntity<Cart> getCartById(@PathVariable("id") Long cartId) {
        Cart cart = cartService.getCartById(cartId);
        return ResponseEntity.ok(cart);
    }

    @Override
    @GetMapping("pending")
    public ResponseEntity<Cart> getPendingCart(Principal principal) {
        Cart cart = cartService.getPendingCart(principal.getName());
        return ResponseEntity.ok(cart);
    }

    @Override
    @GetMapping("user/{email}")
    public ResponseEntity<List<Cart>> getAllCartsByUser(@PathVariable("email") String email) {
        List<Cart> carts = cartService.getAllCartsByUser(email);
        return ResponseEntity.ok(carts);
    }

    @Override
    @GetMapping("user/{email}/pending")
    public ResponseEntity<List<Cart>> getAllPendingCartsByUser(@PathVariable("email") String email) {
        List<Cart> carts = cartService.getAllPendingCartsByUser(email);
        return ResponseEntity.ok(carts);
    }

    @Override
    @GetMapping("status/{cartStatus}/me")
    public ResponseEntity<List<Cart>> getUsersCartsByStatus(@PathVariable("cartStatus") CartStatus cartStatus, Principal principal) {
        return ResponseEntity.ok(cartService.getUsersCartsByStatus(cartStatus, principal));
    }

    @Override
    @PostMapping
    public ResponseEntity<Cart> createCart(@RequestBody Cart cart) {
        Cart createdCart = cartService.createCart(cart);
        return new ResponseEntity<>(createdCart, HttpStatus.CREATED);
    }

    @Override
    @PutMapping("{id}")
    public ResponseEntity<Cart> updateCart(
            @PathVariable("id") Long cartId,
            @RequestBody CartPutRequest cartStatus
    ) {
        Cart updatedCart = cartService.updateCart(cartId, Cart.builder().cartStatus(cartStatus.cartStatus()).build());
        return ResponseEntity.ok(updatedCart);
    }

    @Override
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable("id") Long cartId) {
        cartService.deleteCart(cartId);
        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping("courses/{courseId}/me")
    public ResponseEntity<Boolean> hasBoughtCourse(@PathVariable("courseId") Long courseId, Principal principal) {
        return ResponseEntity.ok(cartService.hasBoughtCourse(courseId, principal));
    }

}
