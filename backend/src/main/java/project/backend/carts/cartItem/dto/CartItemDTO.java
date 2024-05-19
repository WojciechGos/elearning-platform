package project.backend.carts.cartItem.dto;

public record CartItemDTO(
        Long id,
        Long cartId,
        Long courseId) {
}
