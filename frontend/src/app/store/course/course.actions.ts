import { createAction, props} from "@ngrx/store";
import { Course } from "../../interfaces/course.interface";

export const getCourse = createAction('[Course] Get Course');

export const setCourse = createAction('[Course] Set Course', props<{course: Course | null}>());