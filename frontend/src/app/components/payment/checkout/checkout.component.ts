import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { loadStripe, Stripe } from '@stripe/stripe-js'; // Import Stripe type as well
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css'],
})
export class CheckoutComponent {
  stripePromise = loadStripe(environment.stripe);
  stripe: Stripe | null = null; // Declare stripe variable with possible null

  constructor(private http: HttpClient) {
    // Initialize stripe in constructor
    this.stripePromise.then((stripeInstance) => {
      this.stripe = stripeInstance;
    });
  }

  async pay(): Promise<void> {
    const payment = {
      name: 'Iphone',
      currency: 'usd',
      amount: 99900,
      quantity: '1',
      cancelUrl: 'http://localhost:4200/cancel',
      successUrl: 'http://localhost:4200/success',
    };

    // Check if stripe is null before using it
    if (!this.stripe) {
      console.error('Stripe is not loaded yet');
      return;
    }

    try {
      const data: any = await this.http.post(`${environment.apiUrl}/api/v1/payments`, payment).toPromise();
      this.stripe.redirectToCheckout({
        sessionId: data.id,
      });
    } catch (error) {
      console.error('Error occurred while making payment:', error);
    }
  }
}
