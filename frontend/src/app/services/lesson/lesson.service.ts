import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CourseState } from 'src/app/enums/course.state';
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

  updateLesson(lessonId:number, lesson: Lesson): Observable<Lesson> {
    return this.http.put<Lesson>(`${environment.apiUrl}/api/v1/lessons/${lessonId}`, lesson);
  }
  


  getLessonById(courseId:number, lessonId:number): Observable<Lesson> {
    return this.http.get<Lesson>(`${environment.apiUrl}/api/v1/lessons/${lessonId}`);
  }

  getSignedUrlForVideoUpload(lessonId:number): Observable<{signedUrl:string}> {
    return this.http.get<{signedUrl:string}>(`${environment.apiUrl}/api/v1/lessons/${lessonId}/video/upload`);
  }

  getSignedUrlForVideoDownload(lessonId:number): Observable<{url:string}> {
    return this.http.get<{url:string}>(`${environment.apiUrl}/api/v1/lessons/${lessonId}/video/download`);
  }

  uploadVideoToSignedUrl(signedUrl:string, file:File): Observable<HttpResponse<any>> {
    const blob = new Blob([file], { type: 'video/mp4' });
    return this.http.put(signedUrl, blob, { observe: 'response' });
  }

}
