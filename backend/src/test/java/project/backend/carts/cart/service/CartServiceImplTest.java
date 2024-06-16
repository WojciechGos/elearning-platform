package project.backend.carts.cart.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import project.backend.carts.cart.model.Cart;
import project.backend.carts.cart.model.CartStatus;
import project.backend.carts.cart.repository.CartRepository;
import project.backend.carts.cartItem.model.CartItem;
import project.backend.exception.types.ResourceNotFoundException;
import project.backend.user.User;
import project.backend.user.UserService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CartServiceImplTest {

    @Mock
    private CartRepository cartRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private CartServiceImpl cartService;
    private User user;
    private Cart cart;

    @BeforeEach
    void setUp() {
        user = User.builder().id(1L).email("test@example.com").build();
        List<CartItem> cartItems = new ArrayList<>();
        cart = Cart.builder()
                .id(1L)
                .user(user)
                .items(cartItems)
                .totalPrice(BigDecimal.ZERO)
                .cartStatus(CartStatus.PENDING)
                .build();
    }

    @Test
    void getAllCarts() {
        List<Cart> carts = List.of(cart);
        when(cartRepository.findAll()).thenReturn(carts);

        List<Cart> result = cartService.getAllCarts();

        assertEquals(carts, result);
        verify(cartRepository, times(1)).findAll();
    }

    @Test
    void getCartById_ExistingCart() {
        when(cartRepository.findById(cart.getId())).thenReturn(Optional.of(cart));

        Cart result = cartService.getCartById(cart.getId());

        assertEquals(cart, result);
        verify(cartRepository, times(1)).findById(cart.getId());
    }

    @Test
    void getCartById_NonExistingCart() {
        when(cartRepository.findById(cart.getId())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> cartService.getCartById(cart.getId()));
        verify(cartRepository, times(1)).findById(cart.getId());
    }

    @Test
    void createCart() {
        when(cartRepository.save(cart)).thenReturn(cart);

        Cart result = cartService.createCart(cart);

        assertEquals(cart, result);
        verify(cartRepository, times(1)).save(cart);
    }

    @Test
    void updateCart_ExistingCart_NoConflict() {
        Cart cartDetails = Cart.builder()
                .totalPrice(BigDecimal.TEN)
                .cartStatus(CartStatus.COMPLETED)
                .build();
        when(cartRepository.findById(cart.getId())).thenReturn(Optional.of(cart));
        when(cartRepository.save(any(Cart.class))).thenReturn(cart);

        Cart updatedCart = cartService.updateCart(cart.getId(), cartDetails);

        assertEquals(cart.getId(), updatedCart.getId());
        assertEquals(CartStatus.COMPLETED, updatedCart.getCartStatus());

        verify(cartRepository, times(1)).findById(cart.getId());
        verify(cartRepository, times(1)).save(any(Cart.class));
    }

    @Test
    void updateCart_ExistingCart_ConflictingPendingCart() {
        Cart updatedCart = Cart.builder()
                .id(cart.getId())
                .user(cart.getUser())
                .items(cart.getItems())
                .totalPrice(BigDecimal.TEN)
                .cartStatus(CartStatus.PENDING)
                .build();
        when(cartRepository.findById(cart.getId())).thenReturn(Optional.of(cart));
        when(cartRepository.existsByUserIdAndCartStatus(user.getId(), CartStatus.PENDING)).thenReturn(true);

        assertThrows(IllegalStateException.class, () -> cartService.updateCart(cart.getId(), updatedCart));
        verify(cartRepository, times(1)).findById(cart.getId());
        verify(cartRepository, times(1)).existsByUserIdAndCartStatus(user.getId(), CartStatus.PENDING);
        verify(cartRepository, times(0)).save(any(Cart.class));
    }

    @Test
    void deleteCart_ExistingCart() {
        when(cartRepository.existsById(cart.getId())).thenReturn(true);

        cartService.deleteCart(cart.getId());

        verify(cartRepository, times(1)).existsById(cart.getId());
        verify(cartRepository, times(1)).deleteById(cart.getId());
    }

    @Test
    void deleteCart_NonExistingCart() {
        when(cartRepository.existsById(cart.getId())).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> cartService.deleteCart(cart.getId()));
        verify(cartRepository, times(1)).existsById(cart.getId());
        verify(cartRepository, times(0)).deleteById(anyLong());
    }

    @Test
    void getOptionalPendingCartByUserEmail_ExistingCart() {
        when(userService.getUserByEmail(user.getEmail())).thenReturn(user);
        when(cartRepository.findByUserIdAndCartStatus(user.getId(), CartStatus.PENDING)).thenReturn(List.of(cart));

        Optional<Cart> result = cartService.getOptionalPendingCartByUserEmail(user.getEmail());

        assertTrue(result.isPresent());
        assertEquals(cart, result.get());
        verify(userService, times(1)).getUserByEmail(user.getEmail());
        verify(cartRepository, times(1)).findByUserIdAndCartStatus(user.getId(), CartStatus.PENDING);
    }

    @Test
    void getOptionalPendingCartByUserEmail_NoCart() {
        when(userService.getUserByEmail(user.getEmail())).thenReturn(user);
        when(cartRepository.findByUserIdAndCartStatus(user.getId(), CartStatus.PENDING)).thenReturn(List.of());

        Optional<Cart> result = cartService.getOptionalPendingCartByUserEmail(user.getEmail());

        assertFalse(result.isPresent());
        verify(userService, times(1)).getUserByEmail(user.getEmail());
        verify(cartRepository, times(1)).findByUserIdAndCartStatus(user.getId(), CartStatus.PENDING);
    }

    @Test
    void getPendingCart_ExistingCart() {
        when(userService.getUserByEmail(user.getEmail())).thenReturn(user);
        when(cartRepository.findByUserIdAndCartStatus(user.getId(), CartStatus.PENDING)).thenReturn(List.of(cart));

        Cart result = cartService.getPendingCart(user.getEmail());

        assertEquals(cart, result);
        verify(userService, times(1)).getUserByEmail(user.getEmail());
        verify(cartRepository, times(1)).findByUserIdAndCartStatus(user.getId(), CartStatus.PENDING);
    }

    @Test
    void getPendingCart_NoCart() {
        when(userService.getUserByEmail(user.getEmail())).thenReturn(user);
        when(cartRepository.findByUserIdAndCartStatus(user.getId(), CartStatus.PENDING)).thenReturn(List.of());

        assertThrows(ResourceNotFoundException.class, () -> cartService.getPendingCart(user.getEmail()));
        verify(userService, times(1)).getUserByEmail(user.getEmail());
        verify(cartRepository, times(1)).findByUserIdAndCartStatus(user.getId(), CartStatus.PENDING);
    }

    @Test
    void getAllCartsByUser() {
        List<Cart> userCarts = List.of(cart);
        when(userService.getUserByEmail(user.getEmail())).thenReturn(user);
        when(cartRepository.findByUserId(user.getId())).thenReturn(userCarts);

        List<Cart> result = cartService.getAllCartsByUser(user.getEmail());

        assertEquals(userCarts, result);
        verify(userService, times(1)).getUserByEmail(user.getEmail());
        verify(cartRepository, times(1)).findByUserId(user.getId());
    }

    @Test
    void getAllPendingCartsByUser() {
        List<Cart> pendingCarts = List.of(cart);
        when(cartRepository.findByUserEmailAndCartStatus(user.getEmail(), CartStatus.PENDING)).thenReturn(pendingCarts);

        List<Cart> result = cartService.getAllPendingCartsByUser(user.getEmail());

        assertEquals(pendingCarts, result);
        verify(cartRepository, times(1)).findByUserEmailAndCartStatus(user.getEmail(), CartStatus.PENDING);
    }

    @Test
    void getUsersCartsByStatus() {
        List<Cart> userCarts = List.of(cart);
        when(cartRepository.findByUserEmailAndCartStatus(user.getEmail(), cart.getCartStatus())).thenReturn(userCarts);

        List<Cart> result = cartService.getUserCartsByStatus(cart.getCartStatus(), () -> user.getEmail());

        assertEquals(userCarts, result);
        verify(cartRepository, times(1)).findByUserEmailAndCartStatus(user.getEmail(), cart.getCartStatus());
    }

}