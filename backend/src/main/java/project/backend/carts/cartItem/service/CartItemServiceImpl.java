package project.backend.carts.cartItem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.backend.carts.cart.model.Cart;
import project.backend.carts.cart.service.CartService;
import project.backend.carts.cart.model.CartStatus;
import project.backend.carts.cartItem.request.CartItemRequest;
import project.backend.carts.cartItem.model.CartItem;
import project.backend.carts.cartItem.dto.CartItemDTO;
import project.backend.carts.cartItem.repository.CartItemRepository;
import project.backend.courses.course.model.Course;
import project.backend.courses.course.service.CourseService;
import project.backend.exception.ResourceNotFoundException;
import project.backend.user.User;
import project.backend.user.UserService;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CartItemServiceImpl implements CartItemService {
    private final CartItemRepository cartItemRepository;
    private final CartService cartService;
    private final CourseService courseService;
    private final UserService userService;
    private final CartItemMapper cartItemMapper;

    @Override
    public List<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }

    @Override
    public CartItem getCartItemById(Long cartItemId) {
        return cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("CartItem with id [%s] not found.", cartItemId)
                ));
    }

    @Override
    public CartItemDTO createCartItem(CartItemRequest cartItemRequest, Principal principal) {
        Cart cart = getCartFromRequest(cartItemRequest, principal);
        Course course = courseService.getCourseById(cartItemRequest.courseId());
        CartItem cartItem = CartItem.builder()
                .cart(cart)
                .course(course)
                .build();

        cartItem = cartItemRepository.save(cartItem);
        return cartItemMapper.mapToDTO(cartItem);
    }

    private Cart getCartFromRequest(CartItemRequest cartItemRequest, Principal principal) {
        if(principal != null) {
            System.out.println("1 + principal.getName() = " + principal.getName());
            User user = userService.getUserByEmail(principal.getName());
            Optional<Cart> optionalCart = cartService.getOptionalPendingCartByUserEmail(user.getEmail());
            return optionalCart.orElseGet(() -> cartService.createCart(
                    Cart.builder()
                            .user(user)
                            .cartStatus(CartStatus.PENDING)
                            .build()));
        } else if(cartItemRequest.cartId() != null) {
            System.out.println("2");
            return cartService.getCartById(cartItemRequest.cartId());
        } else {
            System.out.println("3");
            return cartService.createCart(
                    Cart.builder()
                            .cartStatus(CartStatus.PENDING)
                            .build());
        }
    }

    @Override
    public CartItem updateCartItem(Long cartItemId, CartItem cartItemDetails) {
        CartItem cartItem = getCartItemById(cartItemId);
        if(cartItemDetails.getCart() != null) cartItem.setCart(cartItemDetails.getCart());
        if(cartItemDetails.getCourse() != null) cartItem.setCourse(cartItemDetails.getCourse());
        return cartItemRepository.save(cartItem);
    }

    @Override
    public void deleteCartItem(Long cartItemId) {
        if (!cartItemRepository.existsById(cartItemId)) {
            throw new ResourceNotFoundException(
                    String.format("CartItem with id [%s] not found.", cartItemId)
            );
        }
        cartItemRepository.deleteById(cartItemId);
    }

}
