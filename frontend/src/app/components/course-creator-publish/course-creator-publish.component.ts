import { Component, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Store, select } from '@ngrx/store';
import { Observable } from 'rxjs';
import { AppStateInterface } from 'src/app/interfaces/appState.interface';
import { Course } from 'src/app/interfaces/course.interface';
import { CourseService } from 'src/app/services/course/course.service';
import { courseSelector } from 'src/app/store/course/course.selectors';

@Component({
  selector: 'app-course-creator-publish',
  templateUrl: './course-creator-publish.component.html',
  styleUrls: ['./course-creator-publish.component.css']
})
export class CourseCreatorPublishComponent {

  @Input() formGroup !: FormGroup;

  course$: Observable<Course | null> = this.store.pipe(select(courseSelector));

  constructor(
    private store: Store<AppStateInterface>,
    private courseService: CourseService
  ) {
  }

  changeStatus(): void {
    this.course$.subscribe((course) => {
      if (course == null) return;

      this.courseService.updateCourseStatus(course.id, this.formGroup.value.courseState).subscribe((course) => {
        console.log(`Course status updated ${course}`);
      });
        
      
    });
  }

}
