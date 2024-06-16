import { Component, OnInit } from '@angular/core';
import { CourseService } from 'src/app/services/course/course.service';
import { Course } from 'src/app/interfaces/course.interface';

@Component({
  selector: 'app-user-profile-completed-courses-list',
  templateUrl: './user-profile-completed-courses-list.component.html',
  styleUrls: ['./user-profile-completed-courses-list.component.css'],
})
export class UserProfileCompletedCoursesListComponent implements OnInit {
  courses: Course[] = [];

  constructor(private courseService: CourseService) {}

  ngOnInit(): void {
    this.courseService.getUsersCompletedCourses().subscribe((courses) => {
      this.courses = courses;
    });
  }
}
