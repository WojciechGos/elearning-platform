package project.backend.stripe;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stripe.exception.StripeException;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "api/v1/payments")
public class StripeController {
    private final StripeService stripeService;

    @PostMapping
    public String payWithCheckoutPage(@RequestBody CheckoutPayment payment) throws StripeException {
        return stripeService.pay(payment);
    }
}
