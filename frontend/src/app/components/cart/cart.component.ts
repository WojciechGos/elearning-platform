import { Component, OnInit } from '@angular/core';
import { CartService } from 'src/app/services/cart.service';
import { PaymentService } from 'src/app/services/payment.service';
import { CartItem } from 'src/app/interfaces/cartItem.interface';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  cartItems: CartItem[] = [];
  totalPrice: number = 0;

  constructor(
    private cartService: CartService,
    private paymentService: PaymentService
  ) {}

  ngOnInit(): void {
     this.loadCart();
  }

  loadCart() {
    this.cartService.getCart().subscribe(cart => {
      this.cartItems = cart.items;
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
      successUrl: 'http://localhost:4200/success',
    };

    this.paymentService.pay(payment);
  }
}
