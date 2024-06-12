package project.backend.stripe;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.stripe.exception.StripeException;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "api/v1/payments")
public class StripeController {
    private final StripeService stripeService;
    @Value("${stripe.public.key}")
    private String stripePublicKey;

    @GetMapping("/stripe-key")
    public ResponseEntity<String> getStripePublicKey() {
        return ResponseEntity.ok(stripePublicKey);
    }

    @PostMapping
    public String payWithCheckoutPage(@RequestBody CheckoutPayment payment) throws StripeException {
        return stripeService.pay(payment);
    }
}
