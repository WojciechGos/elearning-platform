package project.backend.carts.cart.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import project.backend.carts.cart.model.Cart;
import project.backend.carts.cart.repository.CartRepository;
import project.backend.carts.cart.request.CartPutRequest;
import project.backend.carts.cart.service.CartServiceImpl;
import project.backend.carts.cart.model.CartStatus;
import project.backend.config.filter.JwtAuthenticationFilter;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@WebMvcTest(CartControllerImpl.class)
@AutoConfigureMockMvc(addFilters = false)
class CartControllerImplTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CartServiceImpl cartService;

    @MockBean
    private CartRepository cartRepository;

    @MockBean
    private JwtAuthenticationFilter jwtAuthFilter;

    @Test
    void getAllCarts_ReturnsListOfCarts() throws Exception {
        List<Cart> mockCarts = Arrays.asList(
                Cart.builder().id(1L).totalPrice(BigDecimal.valueOf(100)).build(),
                Cart.builder().id(2L).totalPrice(BigDecimal.valueOf(150)).build()
        );
        when(cartService.getAllCarts()).thenReturn(mockCarts);

        mockMvc.perform(get("/api/v1/carts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].totalPrice").value(100))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].totalPrice").value(150));
    }

    @Test
    void getCartById_ReturnsSingleCart() throws Exception {
        long cartId = 1L;
        Cart mockCart = Cart.builder().id(cartId).totalPrice(BigDecimal.valueOf(100)).build();
        when(cartService.getCartById(cartId)).thenReturn(mockCart);

        mockMvc.perform(get("/api/v1/carts/{id}", cartId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(cartId))
                .andExpect(jsonPath("$.totalPrice").value(100));
    }

    @Test
    void getPendingCart_ReturnsPendingCart() throws Exception {
        String username = "testUser";
        Cart mockCart = Cart.builder().id(1L).totalPrice(BigDecimal.valueOf(100)).build();
        when(cartService.getPendingCart(username)).thenReturn(mockCart);

        mockMvc.perform(get("/api/v1/carts/pending").principal(() -> username))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalPrice").value(100));
    }

    @Test
    void getAllCartsByUser_ReturnsListOfCarts() throws Exception {
        String userEmail = "test@example.com";
        List<Cart> mockCarts = Arrays.asList(
                Cart.builder().id(1L).totalPrice(BigDecimal.valueOf(100)).build(),
                Cart.builder().id(2L).totalPrice(BigDecimal.valueOf(150)).build()
        );
        when(cartService.getAllCartsByUser(userEmail)).thenReturn(mockCarts);

        mockMvc.perform(get("/api/v1/carts/user/{email}", userEmail))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].totalPrice").value(100))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].totalPrice").value(150));
    }

    @Test
    void getAllPendingCartsByUser_ReturnsListOfCarts() throws Exception {
        String userEmail = "test@example.com";
        List<Cart> mockCarts = Arrays.asList(
                Cart.builder().id(1L).totalPrice(BigDecimal.valueOf(100)).build(),
                Cart.builder().id(2L).totalPrice(BigDecimal.valueOf(150)).build()
        );
        when(cartService.getAllPendingCartsByUser(userEmail)).thenReturn(mockCarts);

        mockMvc.perform(get("/api/v1/carts/user/{email}/pending", userEmail))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].totalPrice").value(100))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].totalPrice").value(150));
    }

    /*@Test
    void createCart_ReturnsCreatedCart() throws Exception {
        Cart cartRequest = Cart.builder().totalPrice(BigDecimal.valueOf(100)).build();
        Cart createdCart = Cart.builder().id(1L).totalPrice(BigDecimal.valueOf(100)).build();

        when(cartService.createCart(any(Cart.class))).thenReturn(createdCart);

        mockMvc.perform(post("/api/v1/carts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(cartRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.totalPrice").value(100));

        verify(cartService, times(1)).createCart(any(Cart.class));
    }*/

    @Test
    void updateCart_ReturnsUpdatedCart() throws Exception {
        long cartId = 1L;
        CartPutRequest cartPutRequest = new CartPutRequest(CartStatus.PENDING);

        Cart updatedCart = Cart.builder().cartStatus(cartPutRequest.cartStatus()).build();

        when(cartService.updateCart(eq(cartId), any(Cart.class))).thenReturn(updatedCart);

        mockMvc.perform(put("/api/v1/carts/{id}", cartId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(cartPutRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cartStatus").value(CartStatus.PENDING.toString()));
    }

    @Test
    void deleteCart_ReturnsNoContent() throws Exception {
        long cartId = 1L;

        mockMvc.perform(delete("/api/v1/carts/{id}", cartId))
                .andExpect(status().isNoContent());

        verify(cartService, times(1)).deleteCart(cartId);
    }
}
