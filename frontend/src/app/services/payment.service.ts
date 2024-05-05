import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { loadStripe, Stripe } from '@stripe/stripe-js';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {
  stripePromise = loadStripe(environment.stripe);
  stripe: Stripe | null = null;

  constructor(private http: HttpClient) {
    this.stripePromise.then((stripeInstance) => {
      this.stripe = stripeInstance;
    });
  }

  async pay(payment: any): Promise<void> {
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
