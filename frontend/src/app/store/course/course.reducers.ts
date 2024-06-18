import { CourseState } from "../../interfaces/courseState.interface";
import { createReducer, on } from "@ngrx/store";
import { getCourse, setCourse } from "./course.actions";

export const initialState: CourseState = {
    course: null,
    loading: false,
    error: null
};

export const courseReducers = createReducer(
    initialState,
    on(getCourse, (state) => ({ ...state, loading: true })),
    on(setCourse, (state, action) => ({ ...state, loading: true , course: action.course}))
);