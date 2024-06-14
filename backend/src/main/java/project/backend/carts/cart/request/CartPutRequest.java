package project.backend.carts.cart.request;

import project.backend.carts.cart.model.CartStatus;

public record CartPutRequest(
        CartStatus cartStatus
) {
}
