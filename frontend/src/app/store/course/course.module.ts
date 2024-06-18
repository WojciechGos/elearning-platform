import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { EffectsModule } from '@ngrx/effects';
import { StoreModule } from '@ngrx/store';
import { courseReducers } from './course.reducers';
import { CourseService } from '../../services/course/course.service';


@NgModule({
    imports: [
        CommonModule,
        StoreModule.forFeature('course', courseReducers)
    ],
    providers: [CourseService],
    declarations: [],
    exports: []
})

export class CourseModule { }