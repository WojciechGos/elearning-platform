import { Component, OnInit, isDevMode } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CourseService } from 'src/app/services/course/course.service';
import { Course } from 'src/app/interfaces/course.interface';
import { CartService } from 'src/app/services/cart/cart.service';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-course-details',
  templateUrl: './course-details.component.html',
  styleUrls: ['./course-details.component.css']
})
export class CourseDetailsComponent implements OnInit {
  course!: Course;
  isInCart: boolean = false;
  isLoggedIn: boolean = false;
  hasBoughtCourse: boolean = false;
  courseId: number = Number(this.route.snapshot.paramMap.get('id'));
  constructor(
    private courseService: CourseService,
    private cartService: CartService,
    private route: ActivatedRoute,
    private router: Router,
    private authService: AuthService
  ) { }

  ngOnInit(): void {


 
    if (this.courseId) {
      // const numId = Number(courseId);
      this.courseService.getCourseById(this.courseId).subscribe((course) => {
        console.log(course);
        if (course !== undefined)
          this.course = course;
        if (isDevMode() == true) {
          this.course.imageUrl = "./assets/images/course-image.png";
        }
      });

      this.authService.currentUser.subscribe((user) => {
        this.isLoggedIn = !!user;
      });

      this.cartService.isCourseInCart(this.courseId).subscribe((result) => {
        this.isInCart = result;
      });

      this.cartService.hasBoughtCourse(this.courseId).subscribe((result) => {
        console.log(result);
        this.hasBoughtCourse = result;
      });
    }
  }

  addToCart() {
    console.log(this.course.id);
    this.cartService.addCartItem(this.course.id).subscribe(
      (response) => {
        if (!this.isLoggedIn) {
          localStorage.setItem('cartId', response.cartId);
        }
      },
      (error) => {
        console.log("Error:", error);
      }
    );

    this.isInCart = true;
  }

  goToCart() {
    this.router.navigate(['/cart']);
  }
}
