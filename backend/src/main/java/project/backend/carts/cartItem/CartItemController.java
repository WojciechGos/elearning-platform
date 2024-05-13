package project.backend.carts.cartItem;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/cartItems")
public class CartItemController {

    private final CartItemService cartItemService;

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
    public ResponseEntity<CartItemDTO> createCartItem(Principal principal, @RequestBody CartItemRequest cartItemRequest) {
        CartItemDTO createdCartItem = cartItemService.createCartItem(cartItemRequest, principal);
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

