import { Language } from './language.interface';
import { Lesson } from './lesson.interface';

export interface Course {
    id: number
    title: string
    description: string
    price: number
    rating: number
    language: Language
    imageUrl: string
    lessons: Lesson[]
}