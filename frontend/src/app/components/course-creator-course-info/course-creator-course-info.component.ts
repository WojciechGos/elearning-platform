import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Store } from '@ngrx/store';
import { AppStateInterface } from 'src/app/interfaces/appState.interface';
import { Course } from 'src/app/interfaces/course.interface';
import { setCourse } from 'src/app/store/course/course.actions';
import { CourseService } from 'src/app/services/course/course.service';
import { CategoryService } from 'src/app/services/category/category.service';
import { Category } from 'src/app/interfaces/category.interface';


@Component({
  selector: 'app-course-creator-course-info',
  templateUrl: './course-creator-course-info.component.html',
  styleUrls: ['./course-creator-course-info.component.css']
})
export class CourseCreatorCourseInfoComponent implements OnInit {
  @Input() formGroup !: FormGroup;

  
  languages = ['English', 'Spanish', 'Polish'];
  targetAudiences = ['BEGINNER', 'INTERMEDIATE', 'ADVANCED']; 
  categories !: Category[];
  courseImage: File | null = null;

  onFileChange(event: any) {
    const file = event.target.files[0];
    if (file) {
      this.courseImage = file;
    }
  }

  constructor(
    private store: Store<AppStateInterface>,
    private courseService: CourseService,
    private categoryService: CategoryService
  ) { }


  ngOnInit() {
    this.categoryService.getCategories().subscribe((categories) => {
      this.categories= categories;
    });
  }

  // this function can be called from the parent component which is 'course-creator.component.ts'
  createCourse() {
    if (this.formGroup.valid) {

      const newCourse: Course = {
        id:0,
        title: this.formGroup.value.title,
        description: this.formGroup.value.description,
        targetAudience: this.formGroup.value.targetAudience,
        language: this.formGroup.value.language,
        price: this.formGroup.value.price,
        categories: this.formGroup.value.categories,
        rating: 0,
        imageUrl: "",
        discountPrice: 0,
        enrollmentCount: 0,
        lessons: [],
      };
      this.courseService.createCourse(newCourse).subscribe((course) =>
        this.store.dispatch(setCourse({ course })));
    }
  }
}
