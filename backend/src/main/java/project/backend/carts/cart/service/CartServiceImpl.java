package project.backend.carts.cart.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.backend.carts.cart.repository.CartRepository;
import project.backend.carts.cart.model.Cart;
import project.backend.carts.cart.model.CartStatus;

import project.backend.courses.course.model.Course;
import project.backend.exception.types.ForbiddenException;
import project.backend.exception.types.ResourceNotFoundException;
import project.backend.permission.service.PermissionService;
import project.backend.user.User;
import project.backend.user.UserService;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final UserService userService;
    private final PermissionService permissionService;

    @Override
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    @Override
    public Cart getCartById(Long cartId) {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Cart with id [%s] not found.".formatted(cartId)
                ));
    }

    @Override
    public Cart createCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public Cart updateCart(Long cartId, Cart cartDetails) {
        Cart cart = getCartById(cartId);

        if (CartStatus.PENDING == cartDetails.getCartStatus() && cartRepository.existsByUserIdAndCartStatus(cart.getUser().getId(), CartStatus.PENDING)) {
            throw new IllegalStateException("There is already a pending cart for this user.");
        }

        cart.setItems(cartDetails.getItems());
        cart.setTotalPrice(cartDetails.getTotalPrice());
        cart.setCartStatus(cartDetails.getCartStatus());

        return cartRepository.save(cart);
    }

    @Override
    public void deleteCart(Long cartId) {
        if (!cartRepository.existsById(cartId)) {
            throw new ResourceNotFoundException(
                    String.format("Cart with id [%s] not found.", cartId)
            );
        }
        cartRepository.deleteById(cartId);
    }

    @Override
    public Optional<Cart> getOptionalPendingCartByUserEmail(String email) {
        User user = userService.getUserByEmail(email);

        List<Cart> carts = cartRepository.findByUserIdAndCartStatus(user.getId(), CartStatus.PENDING);

        if (carts.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(carts.get(0));
    }

    @Override
    public Cart getPendingCart(String email) {
        User user = userService.getUserByEmail(email);
        List<Cart> carts = cartRepository.findByUserIdAndCartStatus(user.getId(), CartStatus.PENDING);

        if (carts.isEmpty()) {
            throw new ResourceNotFoundException(
                    String.format("Pending cart for user with email [%s] not found.", email)
            );
        }

        return carts.get(0);
    }

    @Override
    public List<Cart> getAllCartsByUser(String email) {
        User user = userService.getUserByEmail(email);

        return cartRepository.findByUserId(user.getId());
    }

    @Override
    public List<Cart> getAllPendingCartsByUser(String email) {
        return cartRepository.findByUserEmailAndCartStatus(email, CartStatus.PENDING);
    }

    @Override
    public List<Cart> getUsersCartsByStatus(CartStatus cartStatus, Principal principal) {
        return cartRepository.findByUserEmailAndCartStatus(principal.getName(), cartStatus);
    }

    @Override
    public boolean hasBoughtCourse(Long courseId, Principal principal) {
        if(principal == null) {
            return false;
        }

        if(permissionService.hasRole(principal, "ROLE_ADMIN")) {
            return true;
        }

        User user = userService.getUserByEmail(principal.getName());


        List<Course> courseList = user.getCourseList();
        Optional<Course> isAuthorOfCourse = courseList.stream().filter(c -> c.getId().equals(courseId)).findFirst();

        if(isAuthorOfCourse.isPresent())
            return true;


        Optional<Cart> cart = cartRepository.findByUserEmailAndCourseId(principal.getName(), courseId);

        if(cart.isEmpty()){
            return false;
        }

        return cart.get().getCartStatus() == CartStatus.COMPLETED;
    }
    @Override
    public List<Cart> getAllCartsByCourseIdAndCartStatus(Long courseId, CartStatus cartStatus) {
        return cartRepository.findAllCartsByCourseIdAndStatus(courseId, cartStatus);
    }
}
