import { createAction, props} from "@ngrx/store";
import { Course } from "../../interfaces/course.interface";

export const getCourse = createAction('[Course] Get Course');

export const setCourse = createAction('[Course] Set Course');


export const createCourse = createAction('[Course] Create Course Success', props<{course: Course}>());
export const createCourseSuccess = createAction('[Course] Create Course Success', props<{course: Course}>());
export const createCourseFailure = createAction('[Course] Create Course Failure', props<{error: string}>());