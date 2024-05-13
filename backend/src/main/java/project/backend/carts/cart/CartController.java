package project.backend.carts.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequestMapping("api/v1/carts")
@RequiredArgsConstructor
@RestController
public class CartController {
    private final CartService cartService;

    @GetMapping
    public ResponseEntity<List<Cart>> getAllCarts() {
        List<Cart> carts = cartService.getAllCarts();
        return ResponseEntity.ok(carts);
    }

    @GetMapping("{id}")
    public ResponseEntity<Cart> getCartById(@PathVariable("id") Long cartId) {
        Cart cart = cartService.getCartById(cartId);
        return ResponseEntity.ok(cart);
    }

    @GetMapping("pending")
    public ResponseEntity<Cart> getPendingCart(Principal principal) {
        Cart cart = cartService.getPendingCart(principal.getName());
        return ResponseEntity.ok(cart);
    }

    @GetMapping("user/{email}")
    public ResponseEntity<List<Cart>> getAllCartsByUser(@PathVariable("email") String email) {
        List<Cart> carts = cartService.getAllCartsByUser(email);
        return ResponseEntity.ok(carts);
    }

    @GetMapping("user/{email}/pending")
    public ResponseEntity<List<Cart>> getAllPendingCartsByUser(@PathVariable("email") String email) {
        List<Cart> carts = cartService.getAllPendingCartsByUser(email);
        return ResponseEntity.ok(carts);
    }

    @PostMapping
    public ResponseEntity<Cart> createCart(@RequestBody Cart cart) {
        Cart createdCart = cartService.createCart(cart);
        return new ResponseEntity<>(createdCart, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Cart> updateCart(
            @PathVariable("id") Long cartId,
            @RequestBody Cart cartDetails
    ) {
        Cart updatedCart = cartService.updateCart(cartId, cartDetails);
        return ResponseEntity.ok(updatedCart);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable("id") Long cartId) {
        cartService.deleteCart(cartId);
        return ResponseEntity.noContent().build();
    }
}
