package project.backend.stripe;

import com.google.gson.Gson;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StripeService {
    @Value("${stripe.secret.key}")
    private String stripeSecretKey;
    private static final Gson gson = new Gson();
    public String pay(CheckoutPayment payment) throws StripeException {
        Stripe.apiKey = stripeSecretKey;

        SessionCreateParams params = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT).setSuccessUrl(payment.successUrl())
                .setCancelUrl(
                        payment.cancelUrl())
                .addLineItem(
                        SessionCreateParams.LineItem.builder().setQuantity(payment.quantity())
                                .setPriceData(
                                        SessionCreateParams.LineItem.PriceData.builder()
                                                .setCurrency(payment.currency()).setUnitAmount(payment.amount())
                                                .setProductData(SessionCreateParams.LineItem.PriceData.ProductData
                                                        .builder().setName(payment.name()).build())
                                                .build())
                                .build())
                .build();

        Session session = Session.create(params);
        Map<String, String> responseData = new HashMap<>();
        responseData.put("id", session.getId());

        return gson.toJson(responseData);
    }
}
