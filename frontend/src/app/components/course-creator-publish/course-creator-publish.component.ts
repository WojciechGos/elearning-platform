import { Component, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Store, select } from '@ngrx/store';
import { Observable } from 'rxjs';
import { AppStateInterface } from 'src/app/interfaces/appState.interface';
import { Course } from 'src/app/interfaces/course.interface';
import { courseSelector } from 'src/app/store/course/course.selectors';

@Component({
  selector: 'app-course-creator-publish',
  templateUrl: './course-creator-publish.component.html',
  styleUrls: ['./course-creator-publish.component.css']
})
export class CourseCreatorPublishComponent {

  @Input() formGroup !: FormGroup;

  course$: Observable<Course | null>;

  constructor(
    private store: Store<AppStateInterface>
  ) {
    this.course$ = this.store.pipe(select(courseSelector));
  }

  publishCourse(): void {
    this.course$.subscribe((course) => {
      if (course != null) {
        console.log(`Course published ${course}`);
      }
    });
  }

}
