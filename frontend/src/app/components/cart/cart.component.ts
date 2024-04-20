import { Component, OnInit } from '@angular/core';
import { Course } from 'src/app/interfaces/course.interface';
import { CourseService } from 'src/app/services/course.service';
import { loadStripe, Stripe } from '@stripe/stripe-js';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  cartItems: Course[] = [];
  totalPrice: number = 0;
  stripePromise = loadStripe(environment.stripe);
  stripe: Stripe | null = null;

  constructor(
    private courseService: CourseService,
    private http: HttpClient
  ) {
    this.stripePromise.then((stripeInstance) => {
      this.stripe = stripeInstance;
    });
  }

  ngOnInit(): void {
    this.courseService.getCourses().subscribe((cartItems) => {
      console.log(cartItems);
      this.cartItems = cartItems;
      this.calculateTotalPrice();
    });
  }

  calculateTotalPrice(): void {
    this.totalPrice = this.cartItems.reduce((total, cartItem) => total + cartItem.price, 0);
  }

  async pay(): Promise<void> {
    const payment = {
      name: this.cartItems.map(cartItem => cartItem.title).join(', '),
      currency: 'usd',
      amount: this.totalPrice * 100,
      quantity: '1',
      cancelUrl: 'http://localhost:4200/cancel',
      successUrl: 'http://localhost:4200/success',
    };

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
