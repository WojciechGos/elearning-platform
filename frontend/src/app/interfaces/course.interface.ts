import { Lesson } from './lesson.interface';
import { User } from './user.interface';
export interface Course {
    id: number;
    title: string;
    description: string;
    price: number;
    discountPrice: number;
    categories: string[];
    language?: string;
    totalDuration?: number;
    rating: number;
    imageUrl: string;
    lessons: Lesson[];
    enrollmentCount?: number;
    courseState?: string;
    targetAudience?: string;
    author?: User;
}