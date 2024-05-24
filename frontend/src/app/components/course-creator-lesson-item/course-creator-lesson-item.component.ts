import { Component, Input, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Store, select } from '@ngrx/store';
import { Observable } from 'rxjs';
import { AppStateInterface } from 'src/app/interfaces/appState.interface';
import { Course } from 'src/app/interfaces/course.interface';
import { LessonService } from 'src/app/services/lesson/lesson.service';
import { courseSelector } from 'src/app/store/course/course.selectors';


@Component({
  selector: 'app-course-creator-lesson-item',
  templateUrl: './course-creator-lesson-item.component.html',
  styleUrls: ['./course-creator-lesson-item.component.css']
})
export class CourseCreatorLessonItemComponent {

  @Input() formGroup !: FormGroup;
  lessonId: number = -1;
  course$: Observable<Course | null>;

  constructor(
    private store: Store<AppStateInterface>,
    private lessonService: LessonService) {
    this.course$ = this.store.pipe(select(courseSelector));
  }



  createOrUpdateLesson(): void {
    if (this.lessonId === -1) {
      this.course$.subscribe((course) => {
        if (course != null) {
          this.lessonService.createLesson(course.id, this.formGroup.value).subscribe((lesson) => {
            console.log(`Lesson created ${lesson}`);
            this.lessonId = lesson.id;
          });
        }
      });
    }else{
      this.course$.subscribe((course) => {
        if (course != null) {
          this.lessonService.updateLesson(course.id, this.lessonId, this.formGroup.value).subscribe((lesson) => {
            console.log(`Lesson updated ${lesson}`);
          });
        }
      });
    }
  }

  onFileChange(event: any): void {
    console.log('File changed');
  }

}
