import { Course } from "src/app/interfaces/course.interface";

export interface CourseState {
  course: Course | null;
  loading: boolean;
  error: string | null;
}