import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router'; 
import { CartService } from 'src/app/services/cart.service';
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
  creationDate!: string;
  cartItems: CartItem[] = [];
  cartStatuses: string[] = ['PENDING', 'COMPLETED'];
  selectedStatus: string = '';

  constructor(
    private route: ActivatedRoute,
    private cartService: CartService,
    private datePipe: DatePipe 
  ) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.id = params['id'];
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
    this.cartService.updateCartStatus(this.id, this.selectedStatus).subscribe(() => {
      console.log('Cart status updated');
    });
  }

}
