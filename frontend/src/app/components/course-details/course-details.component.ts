import { Component, OnInit } from '@angular/core';
import { Type } from '../button/button.types';
import { ActivatedRoute } from '@angular/router'; 
import { CourseService } from 'src/app/services/course.service';
import { Course } from 'src/app/interfaces/course.interface';
@Component({
  selector: 'app-course-details',
  templateUrl: './course-details.component.html',
  styleUrls: ['./course-details.component.css']
})
export class CourseDetailsComponent implements OnInit 
{
  course : Course | undefined; 
  constructor(
    private courseService: CourseService,
    private route: ActivatedRoute
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
    }
  }
  addToCheckoutMethod() {
    console.log('Add to checkout');
  }

  

}
