import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-cart-items-list',
  templateUrl: './cart-items-list.component.html',
  styleUrls: ['./cart-items-list.component.css']
})
export class CartItemsListComponent implements OnInit {
  @Input() cartItems!: any[];
  @Output() cartUpdated = new EventEmitter<void>();

  constructor() { }

  ngOnInit(): void {
  }

  handleCartUpdated(): void {
    this.cartUpdated.emit();
  }

}
