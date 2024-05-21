import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { FormBuilder, Validators, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BreakpointObserver } from '@angular/cdk/layout';
import { StepperOrientation, MatStepperModule } from '@angular/material/stepper';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { AsyncPipe } from '@angular/common';
import { MatStepper } from '@angular/material/stepper';


@Component({
  selector: 'app-course-creator',
  templateUrl: './course-creator.component.html',
  styleUrls: ['./course-creator.component.css']
})
export class CourseCreatorComponent implements OnInit {

  @ViewChild(MatStepper) stepper !: MatStepper;

  constructor(

  ) {
  }
  onStepChange(event: any): void {
    // Prevent changing step by clicking on the step label
    if (event.previouslySelectedIndex !== event.selectedIndex) {
      this.stepper.selectedIndex = event.previouslySelectedIndex;
    }
  }

  ngAfterViewInit() {

    this.stepper.selectedIndex = 0;
  }
  ngOnInit() {
  }
}
