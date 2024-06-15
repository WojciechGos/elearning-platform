import { Component, OnInit, ViewContainerRef, ViewChild, Input } from '@angular/core';
import { Store, select } from '@ngrx/store';
import { Observable } from 'rxjs';
import { AppStateInterface } from 'src/app/interfaces/appState.interface';
import { Course } from 'src/app/interfaces/course.interface';
import { getCourse } from 'src/app/store/course/course.actions';
import { courseSelector } from 'src/app/store/course/course.selectors';
import { FormArray } from '@angular/forms';
import { FormGroup, } from '@angular/forms';
import { getNewLessonFormGroup } from 'src/app/utils/lesson.form';

@Component({
  selector: 'app-course-creator-lesson',
  templateUrl: './course-creator-lesson.component.html',
  styleUrls: ['./course-creator-lesson.component.css']
})
export class CourseCreatorLessonComponent implements OnInit {
  @Input() formArray !: FormArray;
  @ViewChild('courseCreatorLessonItemContainer', { read: ViewContainerRef, static: true }) container!: ViewContainerRef;
  course$: Observable<Course | null>;
  lessonCounter :number = 2;


  constructor(
    private store: Store<AppStateInterface>,
  ) {
    this.course$ = this.store.pipe(select(courseSelector));
  }

 
  get getFormGroups(): FormGroup[] {
    return this.formArray.controls as FormGroup[];
  }
  get getFormArray() : FormArray {
    return this.formArray as FormArray;
  
  }
  ngOnInit(): void {
    console.log(this.formArray)
    this.store.dispatch(getCourse());
  }

  addLesson(): void {
    const newLesson : FormGroup = getNewLessonFormGroup();
    newLesson.controls['lessonNumber'].setValue(this.lessonCounter);

    this.formArray.push(newLesson);
    this.lessonCounter++;
  }

  handleDeleteLessonEvent(index: number): void {
    this.formArray.removeAt(index);
  }
}
