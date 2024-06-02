import { Injectable } from "@angular/core";
import { Actions, createEffect, ofType } from "@ngrx/effects";
import { createCourse, createCourseSuccess, createCourseFailure } from "./course.actions";
import { mergeMap, map, catchError, of } from "rxjs";
import { CourseService } from "../../services/course/course.service";

@Injectable()
export class CourseEffects {
    createCourse$ = createEffect(() =>
        this.actions$.pipe(
            ofType(createCourse), mergeMap((action) => {
                return this.courseService
                    .createCourse(action.course)
                    .pipe(map((course) => createCourseSuccess({ course })),
                        catchError((error) => of(createCourseFailure({ error: error.message }))
                    ));
            }
        )))

    constructor(
        private actions$: Actions,
        private courseService: CourseService
    ) { }
}