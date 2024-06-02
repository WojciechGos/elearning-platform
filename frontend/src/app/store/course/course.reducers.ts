import { CourseState } from "../../interfaces/courseState.interface";
import { createReducer, on } from "@ngrx/store";
import { createCourseFailure, createCourseSuccess, getCourse, setCourse } from "./course.actions";
import { state } from "@angular/animations";

export const initialState: CourseState = {
    course: null,
    loading: false,
    error: null
};

export const courseReducers = createReducer(
    initialState,
    on(getCourse, (state) => ({ ...state, loading: true })),
    on(setCourse, (state, action) => ({ ...state, loading: true , course: action.course})),
    on(createCourseSuccess, (state, action) => ({
        ...state,
        loading: false,
        course: action.course
    })),
    on(createCourseFailure, (state, action) => ({
        ...state,
        loading: false,
        error: action.error
    }))
);