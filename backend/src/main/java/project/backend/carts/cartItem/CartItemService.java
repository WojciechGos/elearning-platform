package project.backend.carts.cartItem;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.backend.carts.cart.Cart;
import project.backend.carts.cart.CartService;
import project.backend.carts.cart.CartStatus;
import project.backend.courses.course.model.Course;
import project.backend.courses.course.service.CourseService;
import project.backend.exception.types.ResourceNotFoundException;
import project.backend.user.User;
import project.backend.user.UserService;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CartItemService {
    private final CartItemRepository cartItemRepository;
    private final CartService cartService;
    private final CourseService courseService;
    private final UserService userService;
    private final CartItemMapper cartItemMapper;

    public List<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }

    public CartItem getCartItemById(Long cartItemId) {
        return cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("CartItem with id [%s] not found.", cartItemId)
                ));
    }

    public CartItemDTO createCartItem(CartItemRequest cartItemRequest, Principal principal) {
        Cart cart;

        if(principal != null) {
            User user = userService.getUserByEmail(principal.getName());

            Optional<Cart> optionalCart = cartService.getOptionalPendingCartByUserEmail(user.getEmail());

            cart = optionalCart.orElseGet(() -> {
                Cart newCart = new Cart();
                newCart.setUser(user);
                newCart.setCartStatus(CartStatus.PENDING);
                return cartService.createCart(newCart);
            });
        } else if(cartItemRequest.cartId() != null) {
            cart = cartService.getCartById(cartItemRequest.cartId());
        } else {
            Cart newCart = new Cart();
            cart = cartService.createCart(newCart);
            cart.setCartStatus(CartStatus.PENDING);
        }

        Course course = courseService.getCourseById(cartItemRequest.courseId());

        CartItem cartItem = new CartItem(
                cart,
                course
        );

        cartItem = cartItemRepository.save(cartItem);
        return cartItemMapper.mapToDTO(cartItem);
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

