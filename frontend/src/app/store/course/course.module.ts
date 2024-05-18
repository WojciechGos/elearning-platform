import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { EffectsModule } from '@ngrx/effects';
import { StoreModule } from '@ngrx/store';
import { courseReducers } from './course.reducers';
import { CourseService } from '../../services/course/course.service';
import { CourseCreatorLessonComponent } from 'src/app/components/course-creator-lesson/course-creator-lesson.component';
import { CourseEffects } from './course.effects';

@NgModule({
    imports: [
        CommonModule,
        StoreModule.forFeature('course', courseReducers),
        EffectsModule.forFeature([CourseEffects]),
    ],
    providers: [CourseService],
    declarations: [],
    exports: []
})

export class CourseModule { }