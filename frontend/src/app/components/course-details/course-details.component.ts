import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router'; 
import { CourseService } from 'src/app/services/course.service';
import { Course } from 'src/app/interfaces/course.interface';
import { CartService } from 'src/app/services/cart.service';

@Component({
  selector: 'app-course-details',
  templateUrl: './course-details.component.html',
  styleUrls: ['./course-details.component.css']
})
export class CourseDetailsComponent implements OnInit 
{
  course! : Course;
  isInCart: boolean = false;

  constructor(
    private courseService: CourseService,
    private cartService: CartService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      const numId = Number(id); // Convert id to number
      this.courseService.getCourseById(numId).subscribe((course) => {
        console.log(course);
        if(course !== undefined)
          this.course = course;
      });

      this.cartService.isCourseInCart(numId).subscribe((result) => {
        this.isInCart = result;
      });
    }
  }
  
  addToCart() {
    this.cartService.addCartItem(this.course.id).subscribe(
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
