package project.backend.carts.cart;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("api/v1/carts")
@RestController
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

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

    @GetMapping("pending/{email}")
    public ResponseEntity<Cart> getPendingCartByUserEmail(@PathVariable("email") String email) {
        Cart cart = cartService.getPendingCartByUserEmail(email);
        return ResponseEntity.ok(cart);
    }

    @GetMapping("user/{email}")
    public ResponseEntity<List<Cart>> getAllCartsByUserEmail(@PathVariable("email") String email) {
        List<Cart> carts = cartService.getAllCartsByUserEmail(email);
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
