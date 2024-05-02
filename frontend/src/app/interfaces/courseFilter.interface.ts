import { Course } from './course.interface';

export interface CourseFilter {
    count: number;
    courses: Course[]
}