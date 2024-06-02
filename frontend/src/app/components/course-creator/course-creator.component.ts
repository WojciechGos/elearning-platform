import { Component, OnInit, ViewChild, forwardRef, AfterViewInit } from '@angular/core';
import { Validators, FormGroup, FormControl, FormArray, NG_VALUE_ACCESSOR } from '@angular/forms';
import { MatStepper } from '@angular/material/stepper';
import { CourseCreatorCourseInfoComponent } from '../course-creator-course-info/course-creator-course-info.component';
import { getNewLessonFormGroup } from 'src/app/utils/lesson.form';
import { CourseState } from 'src/app/enums/course.state';
import { CourseService } from 'src/app/services/course/course.service';
import { Observable } from 'rxjs';
import { Course } from 'src/app/interfaces/course.interface';
import { Store, select } from '@ngrx/store';
import { AppStateInterface } from 'src/app/interfaces/appState.interface';
import { courseSelector } from 'src/app/store/course/course.selectors';
import { setCourse } from 'src/app/store/course/course.actions';

@Component({
  selector: 'app-course-creator',
  templateUrl: './course-creator.component.html',
  styleUrls: ['./course-creator.component.css'],
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => CourseCreatorComponent),
      multi: true,
    }
  ]
})
export class CourseCreatorComponent implements AfterViewInit {

  courseFormGroup = new FormGroup({
    title: new FormControl('', {
      validators: [
        Validators.required,
        Validators.minLength(3),
        Validators.maxLength(30),
      ],
      nonNullable: false,
    }),

    description: new FormControl('', {
      validators: [
        Validators.required
      ],
      nonNullable: false,
    }),

    price: new FormControl(100, {
      validators: [
        Validators.required,
        // Validators.pattern("^[0-9]*$"),
      ],
      nonNullable: true,
    }),

    image: new FormControl('', {
      validators: [
        // Validators.required,
      ],
      nonNullable: true,
    }),

    language: new FormControl('', {
      validators: [
        Validators.required,
      ],
      nonNullable: true,
    }),

    targetAudience: new FormControl('', {
      validators: [
        Validators.required,
      ],
      nonNullable: true,
    }),

    categories: new FormControl([] as string[], {
      validators: [
        Validators.required,
      ],
      nonNullable: true,
    }),
  });



  publishFormGroup = new FormGroup({
    courseState: new FormControl(CourseState, {
      validators: [
        Validators.required
      ],
      nonNullable: true,
    }),
  });

  newCourse: Course = {
    id: 0, // it will be ignored by the backend
    title: '',
    description: '',
    targetAudience: '',
    language: '', // it will be ignored by the backend
    price: 1,
    categories: [], // it will be ignored by the backend
    rating: 0, // it will be ignored by the backend
    imageUrl: "",
    discountPrice: 0, // it will be ignored by the backend
    enrollmentCount: 0, // it will be ignored by the backend
    lessons: [], // it will be ignored by the backend
  };


  lessonsFormArray = new FormArray([] as FormGroup[]);

  @ViewChild(MatStepper) stepper !: MatStepper;
  @ViewChild(CourseCreatorCourseInfoComponent) courseCreatorCourseInfoComponent !: CourseCreatorCourseInfoComponent;
  course$: Observable<Course | null> = this.store.pipe(select(courseSelector));

  constructor(
    private courseService: CourseService,
    private store: Store<AppStateInterface>,
  ) { }



  getOrCreateCourse(): void {
    this.course$.subscribe((course) => {
      if (course != null) return;

      // if user have existing course in state CREATING then load it, else  create it
      this.courseService.getUsersCourses('CREATING').subscribe((courses) => {
        if (courses.length > 0) {
          console.log(courses[0]);
          this.store.dispatch(setCourse({ course: courses[0] }));
          this.courseFormGroup.controls.title.setValue(courses[0].title);
          this.courseFormGroup.controls.description.setValue(courses[0].description);
          this.courseFormGroup.controls.price.setValue(courses[0].price);
          this.courseFormGroup.controls.image.setValue(courses[0].imageUrl);
          this.courseFormGroup.controls.language.setValue(courses[0].language as string);
          this.courseFormGroup.controls.targetAudience.setValue(courses[0].targetAudience as string);
          this.courseFormGroup.controls.categories.setValue(courses[0].categories as string[]);

          courses[0].lessons.forEach((lesson) => {
            const lessonForm: FormGroup = getNewLessonFormGroup();
            lessonForm.controls['id'].setValue(lesson.id);
            lessonForm.controls['title'].setValue(lesson.title);
            lessonForm.controls['description'].setValue(lesson.description);
            lessonForm.controls['videoUrl'].setValue(lesson.videoUrl);
            this.lessonsFormArray.push(lessonForm);
          });
        }
        else {
          this.courseService.createCourse(this.newCourse).subscribe((course) => {
            console.log("coures created");
            this.store.dispatch(setCourse({ course }));
            course.lessons.forEach((lesson) => {
              const lessonForm: FormGroup = getNewLessonFormGroup();
              lessonForm.controls['id'].setValue(lesson.id);
              lessonForm.controls['title'].setValue(lesson.title);
              lessonForm.controls['description'].setValue(lesson.description);
              lessonForm.controls['videoUrl'].setValue(lesson.videoUrl);
              this.lessonsFormArray.push(lessonForm);
            });
          });
        }
      });
    });
  }



  onStepChange(event: any): void {
    console.log('Step changed: ', event);
    // When user decide to change step from 0 to 1 by stepper in header, make sure to create course
    if (event.previouslySelectedIndex === 0) {

    }
  }

  nextStep(type: string): void {
    if (type === 'course') {
      console.log(this.courseFormGroup)
      if (this.courseFormGroup.valid) {
        this.courseCreatorCourseInfoComponent.updateCourseIfFormValid();
        this.stepper.next();
      }
    }
    else if (type === 'lesson') {
      console.log(this.lessonsFormArray)
      if (this.lessonsFormArray.valid) {
        this.stepper.next();
      }
    }

  }


  ngAfterViewInit() {
    this.stepper.selectedIndex = 0;
    this.getOrCreateCourse();
  }

}
