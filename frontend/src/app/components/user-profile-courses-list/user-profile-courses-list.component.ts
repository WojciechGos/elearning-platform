import { Component, OnInit } from '@angular/core';
import { Course } from 'src/app/interfaces/course.interface';
import { CourseService } from 'src/app/services/course/course.service';

@Component({
  selector: 'app-user-profile-courses-list',
  templateUrl: './user-profile-courses-list.component.html',
  styleUrls: ['./user-profile-courses-list.component.css']
})
export class UserProfileCoursesListComponent implements OnInit {

  courses: Course[] = [];

  constructor(private courseService: CourseService) { }

  ngOnInit(): void {

    this.courseService.getUsersCourses("").subscribe((courses) => {
      this.courses = courses;
      console.log(this.courses);
    });
  }

}
