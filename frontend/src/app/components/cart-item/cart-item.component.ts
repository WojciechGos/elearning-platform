import { Component, OnInit, Output, EventEmitter, isDevMode } from '@angular/core';
import { Input } from '@angular/core';
import { CartService } from 'src/app/services/cart/cart.service';

@Component({
  selector: 'app-cart-item',
  templateUrl: './cart-item.component.html',
  styleUrls: ['./cart-item.component.css']
})
export class CartItemComponent implements OnInit {
  @Input() id!: number;
  @Input() title!: string;
  @Input() image!: string;
  @Input() author!: string;
  @Input() price!: number;
  @Input() rating!: number;
  @Output() cartUpdated: EventEmitter<void> = new EventEmitter<void>();

  constructor(private cartService: CartService) { }

  ngOnInit(): void {
    if(isDevMode() == true) {
      this.image="./assets/images/course-image.png"
    }
  }

  removeFromCart() {
    this.cartService.deleteCartItem(this.id).subscribe(() => {
      this.cartUpdated.emit(); 
    });
  }

}
