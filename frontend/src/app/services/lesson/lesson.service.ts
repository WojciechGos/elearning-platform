import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
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

  createEmptyLesson(courseId : number): Observable<Lesson>{
    return this.http.post<Lesson>(`${environment.apiUrl}/api/v1/courses/${courseId}/lessons`, {});
  }

  createLessonWithLessonNumber(courseId: number, lessonNumber: number){
    return this.http.post<Lesson>(`${environment.apiUrl}/api/v1/courses/${courseId}/lessons`, {lessonNumber: lessonNumber});
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

  getSignedUrlForVideoDownload(lessonId:number): Observable<{signedUrl:string}> {
    return this.http.get<{signedUrl:string}>(`${environment.apiUrl}/api/v1/lessons/${lessonId}/video/download`);
  }

  uploadVideoToSignedUrl(signedUrl:string, file:File): Observable<HttpResponse<any>> {
    const blob = new Blob([file], { type: 'video/mp4' });
    const headers = new HttpHeaders().set('Skip-Auth', 'True');
    return this.http.put(signedUrl, blob, { headers, observe: 'response' });
  }

  deleteLesson(lessonId:number): Observable<void> {
    return this.http.delete<void>(`${environment.apiUrl}/api/v1/lessons/${lessonId}`);
  }

}
