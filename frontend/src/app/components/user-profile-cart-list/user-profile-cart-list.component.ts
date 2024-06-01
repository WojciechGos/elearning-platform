import { Component, OnInit } from '@angular/core';
import { Course } from 'src/app/interfaces/course.interface';
import { CartService } from 'src/app/services/cart/cart.service';

@Component({
  selector: 'app-user-profile-cart-list',
  templateUrl: './user-profile-cart-list.component.html',
  styleUrls: ['./user-profile-cart-list.component.css']
})
export class UserProfileCartListComponent implements OnInit {

  course!: Course[];

  constructor(private cartService: CartService) { }

  ngOnInit(): void {

    this.cartService.getUsersCartByStatus("COMPLETED").subscribe((courses) => {
      this.course = courses;
    });
  } 
}
