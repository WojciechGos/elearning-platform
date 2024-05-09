package project.backend.carts.cartItem;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.backend.carts.cart.Cart;
import project.backend.carts.cart.CartService;
import project.backend.carts.cart.CartStatus;
import project.backend.courses.course.Course;
import project.backend.courses.course.CourseService;
import project.backend.exception.ResourceNotFoundException;
import project.backend.user.User;
import project.backend.user.UserService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CartItemService {
    private final CartItemRepository cartItemRepository;
    private final CartService cartService;
    private final CourseService courseService;
    private final UserService userService;

    public List<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }

    public CartItem getCartItemById(Long cartItemId) {
        return cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("CartItem with id [%s] not found.", cartItemId)
                ));
    }

    public CartItem createCartItem(CartItemRequest cartItemRequest) {
        User user = userService.getUserByEmail(cartItemRequest.email());

        Optional<Cart> optionalCart = cartService.getOptionalPendingCartByUserEmail(user.getEmail());

        Cart cart = optionalCart.orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setUser(user);
            newCart.setCartStatus(CartStatus.PENDING);
            return cartService.createCart(newCart);
        });

        Course course = courseService.getCourseById(cartItemRequest.courseId());

        CartItem cartItem = new CartItem(
                cart,
                course
        );

        return cartItemRepository.save(cartItem);
    }

    public CartItem updateCartItem(Long cartItemId, CartItem cartItemDetails) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("CartItem with id [%s] not found.", cartItemId)
                ));

        cartItem.setCart(cartItemDetails.getCart());
        cartItem.setCourse(cartItemDetails.getCourse());

        return cartItemRepository.save(cartItem);
    }

    public void deleteCartItem(Long cartItemId) {
        if (!cartItemRepository.existsById(cartItemId)) {
            throw new ResourceNotFoundException(
                    String.format("CartItem with id [%s] not found.", cartItemId)
            );
        }
        cartItemRepository.deleteById(cartItemId);
    }
}

