import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CartService } from 'src/app/services/cart.service';

@Component({
  selector: 'app-success',
  templateUrl: './success.component.html',
  styleUrls: ['./success.component.css']
})
export class SuccessComponent implements OnInit {
  cartId!: number;

  constructor(private route: ActivatedRoute, private cartService: CartService) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe((params) => {
      this.cartId = params['cartId'];
    });

    this.cartService.updateCartStatus(this.cartId, 'COMPLETED').subscribe(
      (error) => {
        console.log("Error:", error);
      }
    );
  }
  
}
