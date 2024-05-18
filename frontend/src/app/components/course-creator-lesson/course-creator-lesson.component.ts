import { Component, OnInit } from '@angular/core';
import { Store, select } from '@ngrx/store';
import { Observable } from 'rxjs';
import { AppStateInterface } from 'src/app/interfaces/appState.interface';
import { Course } from 'src/app/interfaces/course.interface';
import { getCourse } from 'src/app/store/course/course.actions';
import { courseSelector } from 'src/app/store/course/course.selectors';

@Component({
  selector: 'app-course-creator-lesson',
  templateUrl: './course-creator-lesson.component.html',
  styleUrls: ['./course-creator-lesson.component.css']
})
export class CourseCreatorLessonComponent implements OnInit {
  
  course$: Observable<Course|null>;

  constructor(private store: Store<AppStateInterface>) { 
    this.course$ = this.store.pipe(select(courseSelector));
  }

  ngOnInit(): void {
    this.store.dispatch(getCourse());
  }

}
