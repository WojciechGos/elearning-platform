import { Component, OnInit } from '@angular/core';
import { CartService } from 'src/app/services/cart/cart.service';

@Component({
  selector: 'app-user-profile-cart-list',
  templateUrl: './user-profile-cart-list.component.html',
  styleUrls: ['./user-profile-cart-list.component.css']
})
export class UserProfileCartListComponent implements OnInit {

  carts: any[] = [];

  constructor(private cartService: CartService) { }

  ngOnInit(): void {

    this.cartService.getUsersCartByStatus("COMPLETED").subscribe((carts) => {
      this.carts = carts;
      console.log(this.carts);
    });
  } 
}
