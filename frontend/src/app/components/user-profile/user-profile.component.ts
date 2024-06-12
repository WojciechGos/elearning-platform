import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Store } from '@ngrx/store';
import { AppStateInterface } from 'src/app/interfaces/appState.interface';
import { AuthService } from 'src/app/services/auth.service';
import { CourseService } from 'src/app/services/course/course.service';
import { setCourse } from 'src/app/store/course/course.actions';

import { Router } from '@angular/router';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css'],
})
export class UserProfileComponent implements OnInit {
  user: any;

  constructor(
    private authService: AuthService,
    private courseService: CourseService,
    private store: Store<AppStateInterface>,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.authService.currentUser.subscribe((user) => {
      this.user = user;
    });
  }

  logout(): void {
    this.authService.logout().subscribe(
      (response) => {
        console.log('Logged out');
      },
      (error) => {
        console.error('Logout failed', error);
      }
    );
  }

  createCourse(){
    this.courseService.createEmptyCourse().subscribe((course) => {
      this.store.dispatch(setCourse({ course }));
      this.router.navigateByUrl('/course-creator');
    });
  }
}
