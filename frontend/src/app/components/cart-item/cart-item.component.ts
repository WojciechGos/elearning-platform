import { Component, OnInit } from '@angular/core';
import { Input } from '@angular/core';

@Component({
  selector: 'app-cart-item',
  templateUrl: './cart-item.component.html',
  styleUrls: ['./cart-item.component.css']
})
export class CartItemComponent implements OnInit {
  @Input() title!: string;
  @Input() image!: string;
  @Input() author!: string;
  @Input() price!: number;
  @Input() rating!: number;

  constructor() { }

  ngOnInit(): void {
  }

}
