package project.backend.carts.cartItem;

public record CartItemRequest(
        Long cartId,
        Long courseId,
        String email
) {
}
