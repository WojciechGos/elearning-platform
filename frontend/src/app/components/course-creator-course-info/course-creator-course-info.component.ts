import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Store } from '@ngrx/store';
import { AppStateInterface } from 'src/app/interfaces/appState.interface';
import { Course } from 'src/app/interfaces/course.interface';
import { createCourse } from 'src/app/store/course/course.actions';

@Component({
  selector: 'app-course-creator-course-info',
  templateUrl: './course-creator-course-info.component.html',
  styleUrls: ['./course-creator-course-info.component.css']
})
export class CourseCreatorCourseInfoComponent implements OnInit {

  selectedTargetAudience !: string;
  targetAudiences: string[] = ['BEGGINNER', 'INTERMEDIATE', 'ADVANCED'];
  selectedLanguage !: string;
  languages: string[] = ['Polish', 'English', "Spanish"];
  firstFormGroup!: FormGroup;
  isEditable = false;

  constructor(private store: Store<AppStateInterface>) { }


  ngOnInit() {

  }

  createCourse() {
    // const course : Course = {
    //   title: this.firstFormGroup.value.title,

    // };
    // this.store.dispatch(createCourse());
  }

}
