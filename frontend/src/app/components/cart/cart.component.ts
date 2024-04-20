import { Component, OnInit } from '@angular/core';
import { Course } from 'src/app/interfaces/course.interface';
import { CourseService } from 'src/app/services/course.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  cartItems: Course[] = [];
  totalPrice: number = 0;

  constructor(private courseService: CourseService) { }

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

}
