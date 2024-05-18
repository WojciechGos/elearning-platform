package project.backend.carts.cartItem.request;

public record CartItemRequest(
        Long courseId,
        Long cartId
) {
}
