import { Component, OnInit } from '@angular/core';
import { CartService } from 'src/app/services/cart/cart.service';
import { PaymentService } from 'src/app/services/payment/payment.service';
import { CartItem } from 'src/app/interfaces/cartItem.interface';
import { AuthService } from 'src/app/services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  cartItems: CartItem[] = [];
  totalPrice: number = 0;
  cartId!: number;
  isLoggedIn: boolean = false;

  constructor(
    private cartService: CartService,
    private paymentService: PaymentService,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
     this.loadCart();

    this.authService.currentUser.subscribe((user) => {
      this.isLoggedIn = !!user;
    });
  }

  loadCart() {
    this.cartService.getCart().subscribe(cart => {
      this.cartItems = cart.items;
      this.cartId = cart.id;
      this.calculateTotalPrice();
    });
  }

  calculateTotalPrice() {
    this.totalPrice = this.cartItems.reduce((total, cartItem) => {
      return total + cartItem.course.price;
    }, 0);
  }

  pay(): void {
    const payment = {
      name: this.cartItems.map(cartItem => cartItem.course.title).join(', '),
      currency: 'usd',
      amount: this.totalPrice * 100,
      quantity: '1',
      cancelUrl: 'http://localhost:4200/cancel',
      successUrl: `http://localhost:4200/success?cartId=${this.cartId}`,
    };

    this.paymentService.pay(payment);
  }

  goToLogin() {
    this.router.navigate(['/login']); 
  }

}
