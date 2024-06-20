import { Component, OnInit, ViewChild, forwardRef, AfterViewInit, OnDestroy } from '@angular/core';
import { Validators, FormGroup, FormControl, FormArray, NG_VALUE_ACCESSOR } from '@angular/forms';
import { MatStepper } from '@angular/material/stepper';
import { CourseCreatorCourseInfoComponent } from '../course-creator-course-info/course-creator-course-info.component';
import { getNewLessonFormGroup } from 'src/app/utils/lesson.form';
import { CourseState } from 'src/app/enums/course.state';
import { CourseService } from 'src/app/services/course/course.service';
import { Observable, Subject, Subscription, takeUntil } from 'rxjs';
import { Course } from 'src/app/interfaces/course.interface';
import { Store, select } from '@ngrx/store';
import { AppStateInterface } from 'src/app/interfaces/appState.interface';
import { courseSelector } from 'src/app/store/course/course.selectors';
import { Router } from '@angular/router';
import { setCourse } from 'src/app/store/course/course.actions';
import { PermissionService } from 'src/app/services/permission/permission.service';

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
export class CourseCreatorComponent implements OnInit, OnDestroy {

  private courseSubscription?: Subscription; 

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
  private destroy$ = new Subject<void>();
  isAdmin: boolean = false;

  constructor(
    private courseService: CourseService,
    private permissionService: PermissionService,
    private store: Store<AppStateInterface>,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.course$.pipe(takeUntil(this.destroy$)).subscribe((course) => {
      if (course === null)
        this.router.navigateByUrl('/user-profile');
      else
        this.getCourse();
    });

    this.permissionService.checkUserPermission('ROLE_ADMIN').subscribe((response) => {
      console.log(response);
      this.isAdmin = response;
    })
  }

  getCourse(): void {
    this.course$.subscribe((course) => {


      // TODO: add some sophisticated information for the user  
      if (course === null){
        console.log("Course is null");
        return;
      }

      this.courseSubscription = this.courseService.getCourseById(course.id as number).subscribe((course) => {

        this.courseFormGroup.controls.title.setValue(course.title);
        this.courseFormGroup.controls.description.setValue(course.description);
        this.courseFormGroup.controls.price.setValue(course.price);
        this.courseFormGroup.controls.image.setValue(course.imageUrl);
        this.courseFormGroup.controls.language.setValue(course.language as string);
        this.courseFormGroup.controls.targetAudience.setValue(course.targetAudience as string);
        this.courseFormGroup.controls.categories.setValue(course.categories as string[]);
        this.courseCreatorCourseInfoComponent.subscribeSelectedOptionsChange();
        course.lessons.forEach((lesson) => {
          const lessonForm: FormGroup = getNewLessonFormGroup();
          lessonForm.controls['id'].setValue(lesson.id);
          lessonForm.controls['title'].setValue(lesson.title);
          lessonForm.controls['description'].setValue(lesson.description);
          lessonForm.controls['videoUrl'].setValue(lesson.videoUrl);
          this.lessonsFormArray.push(lessonForm);
        });
      });
    });
  }



  onStepChange(event: any): void {
    console.log('Step changed: ', event);
    // When user decide to change step from 0 to 1 by stepper in header, make sure to create course
    if (event.previouslySelectedIndex === 0) {

    }
  }



  // TODO unsubscibe services that connect with API
  ngOnDestroy(): void {
    console.log("Destroying course creator");
    this.courseFormGroup.reset();
    this.lessonsFormArray.clear();
    this.publishFormGroup.reset();
    this.store.dispatch(setCourse({course : null}));
    this.courseSubscription?.unsubscribe();
    this.destroy$.next();
    this.destroy$.complete();
  }
}
