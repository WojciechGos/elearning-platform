import { Component, OnInit } from '@angular/core';
import { Course } from 'src/app/interfaces/course.interface';
import { CourseService } from 'src/app/services/course/course.service';
import { Router } from '@angular/router';
import { Store } from '@ngrx/store';
import { AppStateInterface } from 'src/app/interfaces/appState.interface';
import { setCourse } from 'src/app/store/course/course.actions';

@Component({
  selector: 'app-user-profile-courses-list',
  templateUrl: './user-profile-courses-list.component.html',
  styleUrls: ['./user-profile-courses-list.component.css']
})
export class UserProfileCoursesListComponent implements OnInit {

  courses: Course[] = [];

  constructor(
    private courseService: CourseService,
    private router: Router,
    private store: Store<AppStateInterface>,
  ) { }

  ngOnInit(): void {

    this.courseService.getUsersCourses("").subscribe((courses) => {
      this.courses = courses;
      console.log(this.courses);
    });

  }
  
  editCourse(course: Course) : void{
    this.store.dispatch(setCourse({ course }));
    this.router.navigateByUrl('/course-creator');
  }
}
