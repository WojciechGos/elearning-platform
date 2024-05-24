import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Lesson } from 'src/app/interfaces/lesson.interface';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class LessonService {

  constructor(
    private http: HttpClient
  ) { }

  createLesson(courseId:number, lesson: Lesson): Observable<Lesson> {
    return this.http.post<Lesson>(`${environment.apiUrl}/api/v1/courses/${courseId}/lessons`, lesson);
  }

  updateLesson(courseId:number, lessonId:number, lesson: Lesson): Observable<Lesson> {
    return this.http.put<Lesson>(`${environment.apiUrl}/api/v1/courses/${courseId}/lessons/${lessonId}`, lesson);
  }
}
