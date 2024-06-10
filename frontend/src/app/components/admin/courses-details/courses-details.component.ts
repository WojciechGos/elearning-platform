import { Component, OnInit } from '@angular/core';
import { Course } from 'src/app/interfaces/course.interface';
import { CourseService } from 'src/app/services/course/course.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Store } from '@ngrx/store';
import { AppStateInterface } from 'src/app/interfaces/appState.interface';
import { setCourse } from 'src/app/store/course/course.actions';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { CourseState } from 'src/app/enums/course.state';

@Component({
  selector: 'app-courses-details',
  templateUrl: './courses-details.component.html',
  styleUrls: ['./courses-details.component.css']
})
export class CoursesDetailsComponent implements OnInit {

  course !: Course

  formGroup: FormGroup = new FormGroup({
    courseState: new FormControl('', {
      validators: [
        Validators.required
      ],
      nonNullable: true,
    }),
  });

  constructor(
    private courseService: CourseService,
    private route: ActivatedRoute,
    private store: Store<AppStateInterface>,
    private router: Router
  ) { }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    this.courseService.getCourseById(parseInt(id as string)).subscribe((course) => {
      this.course = course;
    });
  }
  editCourse(course: Course): void {
    this.store.dispatch(setCourse({ course }));
    this.router.navigateByUrl('/course-creator');
  }

  changeStatus(): void {

    this.courseService.updateCourseStatus(this.course.id, this.formGroup.value.courseState).subscribe((course) => {
      console.log(`Course status updated ${course}`);
    });

  }
}