import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { FormBuilder, Validators, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatStepper } from '@angular/material/stepper';
import { CourseCreatorCourseInfoComponent } from '../course-creator-course-info/course-creator-course-info.component';

@Component({
  selector: 'app-course-creator',
  templateUrl: './course-creator.component.html',
  styleUrls: ['./course-creator.component.css']
})
export class CourseCreatorComponent implements OnInit {

  courseFormGroup = this._formBuilder.group({
    title: ['', Validators.required],
    description: ['', Validators.required],
    price: ['', Validators.required],
    image: [''],
    language: ['', Validators.required],
    targetAudience: ['', Validators.required],
    categories: [[], Validators.required]
  });

  secondFormGroup = this._formBuilder.group({
    secondCtrl: ['', Validators.required],
  });
  thirdFormGroup = this._formBuilder.group({
    thirdCtrl: ['', Validators.required],
  });

  @ViewChild(MatStepper) stepper !: MatStepper;
  @ViewChild(CourseCreatorCourseInfoComponent) courseCreatorCourseInfoComponent !: CourseCreatorCourseInfoComponent;

  constructor(private _formBuilder: FormBuilder) {}

  onStepChange(event: any): void {
    console.log('Step changed: ', event);
    // When user decide to change step from 0 to 1 by stepper in header, make sure to create course
    if(event.previouslySelectedIndex === 0) {
      this.courseCreatorCourseInfoComponent.createCourse();
    }
  }

  ngAfterViewInit() {

    this.stepper.selectedIndex = 0;
  }
  ngOnInit() {
  }
}
