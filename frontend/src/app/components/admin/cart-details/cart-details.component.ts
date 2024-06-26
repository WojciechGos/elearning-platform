import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router'; 
import { CartService } from 'src/app/services/cart/cart.service';
import { CartItem } from 'src/app/interfaces/cartItem.interface';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-cart-details',
  templateUrl: './cart-details.component.html',
  styleUrls: ['./cart-details.component.css'],
  providers: [DatePipe]
})
export class CartDetailsComponent implements OnInit {
  id!: number;
  email!: string;
  creationDate!: string;
  cartItems: CartItem[] = [];
  cartStatuses: string[] = ['PENDING', 'COMPLETED', 'CANCELLED'];
  selectedStatus: string = '';

  constructor(
    private route: ActivatedRoute,
    private cartService: CartService,
    private datePipe: DatePipe 
  ) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.id = params['id'];
      this.email = params['email'];
      this.loadCart();
    });
  }

  loadCart(): void {
    this.cartService.getCartByCartID(this.id).subscribe(cart => {
      this.cartItems = cart.items;
      this.selectedStatus = cart.cartStatus;
      this.creationDate = this.datePipe.transform(
        cart.createdOn,
        'yyyy-MM-dd HH:mm:ss'
      )!;
    });
  }

  onStatusSelected() { 
    if (this.selectedStatus === 'PENDING') {
      this.cartService.getPendingCartsByUser(this.email).subscribe(carts => {
        if (carts.length > 0) {
          alert('There is already a cart with PENDING status');
          this.loadCart();
          return;
        }
        
        this.updateCartStatus();
      });
    } else {
      this.updateCartStatus();
    }
  }

  updateCartStatus() {
    this.cartService.updateCartStatus(this.id, this.selectedStatus).subscribe(() => {
      console.log('Cart status updated');
    });
  }

}
