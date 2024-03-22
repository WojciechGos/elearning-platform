package project.backend.carts.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CartService {
    private final CartRepository cartRepository;
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

//    public Cart getCartById(Long cartId) {
//        return cartRepository.findById(cartId)
//                .orElseThrow(() -> new Reso)
//    }
}
