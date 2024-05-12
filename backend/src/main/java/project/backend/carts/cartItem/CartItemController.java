package project.backend.carts.cartItem;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/v1/cartItems")
public class CartItemController {

    private final CartItemService cartItemService;

    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @GetMapping
    public ResponseEntity<List<CartItem>> getAllCartItems() {
        List<CartItem> cartItems = cartItemService.getAllCartItems();
        return ResponseEntity.ok(cartItems);
    }

    @GetMapping("{id}")
    public ResponseEntity<CartItem> getCartItemById(@PathVariable("id") Long cartItemId) {
        CartItem cartItem = cartItemService.getCartItemById(cartItemId);
        return ResponseEntity.ok(cartItem);
    }

    @PostMapping
    public ResponseEntity<CartItem> createCartItem(Principal principal, @RequestBody CartItemRequest cartItemRequest) {
        CartItem createdCartItem = cartItemService.createCartItem(cartItemRequest, principal.getName());
        return new ResponseEntity<>(createdCartItem, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartItem> updateCartItem(@PathVariable("id") Long cartItemId, @RequestBody CartItem cartItemDetails) {
        CartItem updatedCartItem = cartItemService.updateCartItem(cartItemId, cartItemDetails);
        return ResponseEntity.ok(updatedCartItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCartItem(@PathVariable("id") Long cartItemId) {
        cartItemService.deleteCartItem(cartItemId);
        return ResponseEntity.noContent().build();
    }
}

