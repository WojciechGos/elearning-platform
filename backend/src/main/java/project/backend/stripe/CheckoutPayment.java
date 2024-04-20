package project.backend.stripe;

public record CheckoutPayment(
    String name,
    String currency,
    String successUrl,
    String cancelUrl,
    long amount,
    long quantity
) {
}
