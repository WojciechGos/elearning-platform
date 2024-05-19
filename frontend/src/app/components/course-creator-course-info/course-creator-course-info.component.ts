import { Component, OnInit } from '@angular/core';
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

  createCourse(form: any) {
    if (form.valid) {
      const formData = { ...form.value, courseImage: this.courseImage };
      console.log(formData);


      console.log('Creating course')
      console.log(form.value.categories)
      const newCourse: Course = {
        id:0,
        title: form.value.title,
        description: form.value.description,
        targetAudience: form.value.targetAudience,
        language: form.value.language,
        price: form.value.price,
        categories: form.value.categories,
        rating: 0,
        imageUrl: "",
        discountPrice: 0,
        enrollmentCount: 0,
        lessons: [],
      };
      this.courseService.createCourse(newCourse).subscribe((course) =>
        this.store.dispatch(setCourse({ course })));


    } else {
      console.log('Form is not valid');
    }
  }
}
