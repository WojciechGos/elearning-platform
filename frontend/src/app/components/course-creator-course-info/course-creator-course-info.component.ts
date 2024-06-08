import { Component, OnInit, Input, forwardRef, ViewChild, OnDestroy } from '@angular/core';
import { FormGroup, NG_VALUE_ACCESSOR } from '@angular/forms';
import { Store, select } from '@ngrx/store';
import { AppStateInterface } from 'src/app/interfaces/appState.interface';
import { Course } from 'src/app/interfaces/course.interface';
import { CourseService } from 'src/app/services/course/course.service';
import { CategoryService } from 'src/app/services/category/category.service';
import { Category } from 'src/app/interfaces/category.interface';
import { Observable, Subscription } from 'rxjs';
import { courseSelector } from 'src/app/store/course/course.selectors';
import { MatSelectionList } from '@angular/material/list';
import { Subject } from 'rxjs';
import { take, takeUntil } from 'rxjs/operators';


@Component({
  selector: 'app-course-creator-course-info',
  templateUrl: './course-creator-course-info.component.html',
  styleUrls: ['./course-creator-course-info.component.css'],
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => CourseCreatorCourseInfoComponent),
      multi: true,
    }
  ]
})
export class CourseCreatorCourseInfoComponent implements OnInit, OnDestroy {

  @Input() formGroup !: FormGroup;
  @ViewChild('selectionList') selectionList !: MatSelectionList;
  course$: Observable<Course | null> = this.store.pipe(select(courseSelector));
  categoriesLoad: boolean = false;
  private destroy$ = new Subject<void>();
  private addCategorySubscription?: Subscription;
  private removeCategorySubscription?: Subscription;

  languages = ['English', 'Spanish', 'Polish'];
  targetAudiences = ['BEGINNER', 'INTERMEDIATE', 'ADVANCED'];
  categories !: Category[];
  courseImage: File | null = null;
  imageUrl !: string;

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

  constructor(
    private store: Store<AppStateInterface>,
    private courseService: CourseService,
    private categoryService: CategoryService
  ) { }


  ngOnInit() {
    this.categoryService.getCategories().subscribe((categories) => {
      this.categories = categories;
    });


  }

  subscribeSelectedOptionsChange(): void {
    console.log('categories loaded');
    this.selectionList.selectedOptions.changed
      .pipe(takeUntil(this.destroy$))
      .subscribe((event) => {

        if (event.added.length > 0) {
          const category = event.added[0].value as string;

          this.addCategoryToCourse(this.findCategoryByName(category) as Category);
        }
        else if (event.removed.length > 0) {
          const category = event.removed[0].value as string;
          this.removeCategoryFromCourse(this.findCategoryByName(category) as Category);
        }
      });
  }


  addCategoryToCourse(category: Category): void {
    this.course$.pipe(take(1)).subscribe((course) => {
      if (course == null) return;
  
      this.addCategorySubscription = this.categoryService.addCategoryToCourse(category.id, course.id).subscribe((response) => {
        console.log(`Category ${category.name} added to course ${course.title}`);
      });
    });
  }

  removeCategoryFromCourse(category: Category): void {
    this.course$.pipe(take(1)).subscribe((course) => {
      if (course == null) return;
  
      this.removeCategorySubscription = this.categoryService.removeCategoryFromCourse(category.id, course.id).subscribe((response) => {
        console.log(`Category ${category.name} removed from course ${course.title}`);
      });
    });
  }

  findCategoryByName(name: string): Category | undefined {
    return this.categories.find((category) => category.name === name);
  }

  onFileChange(event: any) {
    const file = event.target.files[0];

    this.course$.subscribe((course) => {

      if (course == null) return;
      // this.courseService.createCourse(this.formGroup.value).subscribe((lesson) => {
      //   this.lessonId = lesson.id;
      //   this.uploadVideo(file);
      // });

      this.uploadImage(course.id, file);
    });
  }

  uploadImage(courseId: number, file: File): void {

    this.courseService.getSignedUrlForImageUpload(courseId).subscribe((response) => {
      this.courseService.uploadImageToSignedUrl(response.signedUrl, file).subscribe((s3Response) => {
        this.courseService.getCourseById(courseId).subscribe((course) => {
          this.imageUrl = course.imageUrl;
        });
      });
    });
  }

  updateCourse(courseId: number): void {


    this.newCourse.title = this.formGroup.value.title;
    this.newCourse.description = this.formGroup.value.description;
    this.newCourse.targetAudience = this.formGroup.value.targetAudience;
    this.newCourse.language = this.formGroup.value.language;
    this.newCourse.price = this.formGroup.value.price;

    this.courseService.updateCourse(courseId, this.newCourse).subscribe((course) => {
      console.log("course updated")
      console.log(course);
    });

  }



  // this function can be called from the parent component which is 'course-creator.component.ts'
  updateCourseIfFormValid(): void {
    console.log("update course")
    if (!this.formGroup.valid) {
      console.log("form is not valid")
    }

    this.course$.pipe(takeUntil(this.destroy$)).subscribe((course) => {
      if (course == null) {
        console.log('course null')
        return
      }
      this.updateCourse(course.id);
    });
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
    this.addCategorySubscription?.unsubscribe();
    this.removeCategorySubscription?.unsubscribe();
  }
}
