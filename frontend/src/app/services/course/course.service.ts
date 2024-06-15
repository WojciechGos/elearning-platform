import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Course } from '../../interfaces/course.interface';
import { environment } from 'src/environments/environment';
import { CourseFilter } from '../../interfaces/courseFilter.interface';
import { CourseState } from 'src/app/enums/course.state';

@Injectable({
  providedIn: 'root',
})
export class CourseService {
  constructor(private http: HttpClient) {}

  getCoursesByFilter(params?: any): Observable<CourseFilter> {
    return this.http.get<CourseFilter>(`${environment.apiUrl}/api/v1/courses`, {
      params,
    });
  }

  getCourses(): Observable<Course[]> {
    return this.http.get<Course[]>(`${environment.apiUrl}/api/v1/courses`);
  }

  getCourseById(id: number): Observable<Course> {
    return this.http.get<Course>(`${environment.apiUrl}/api/v1/courses/${id}`);
  }

  createCourse(course: Course): Observable<Course> {
    return this.http.post<Course>(
      `${environment.apiUrl}/api/v1/courses`,
      course
    );
  }

  createEmptyCourse(): Observable<Course> {
    return this.http.post<Course>(`${environment.apiUrl}/api/v1/courses`, {});
  }

  updateCourse(id: number, course: Course): Observable<Course> {
    return this.http.put<Course>(
      `${environment.apiUrl}/api/v1/courses/${id}`,
      course
    );
  }

  updateCourseStatus(id: number, courseState: CourseState): Observable<Course> {
    return this.http.put<Course>(`${environment.apiUrl}/api/v1/courses/${id}`, {
      courseState,
    });
  }

  uploadImageToSignedUrl(signedUrl: string, file: File): Observable<any> {
    const blob = new Blob([file], { type: 'image/png, image/jpeg, image/jpg' });
    const headers = new HttpHeaders().set('Skip-Auth', 'True');
    return this.http.put(signedUrl, blob, { headers, observe: 'response' });
  }

  getSignedUrlForImageUpload(
    courseId: number
  ): Observable<{ signedUrl: string }> {
    return this.http.get<{ signedUrl: string }>(
      `${environment.apiUrl}/api/v1/courses/${courseId}/image`
    );
  }

  deleteCourseImage(courseId: number): Observable<{}> {
    return this.http.delete<{}>(
      `${environment.apiUrl}/api/v1/courses/${courseId}/image`
    );
  }

  getUsersCourses(courseState: string): Observable<Course[]> {
    if (courseState === '')
      return this.http.get<Course[]>(
        `${environment.apiUrl}/api/v1/courses/users`
      );
    else
      return this.http.get<Course[]>(
        `${environment.apiUrl}/api/v1/courses/users?courseState=${courseState}`
      );
  }

  completeCourse(courseId: number): Observable<any> {
    return this.http.put<any>(
      `${environment.apiUrl}/api/v1/courses/${courseId}/complete`,
      {}
    );
  }

  getUsersCompletedCourses(): Observable<Course[]> {
    return this.http.get<Course[]>(
      `${environment.apiUrl}/api/v1/courses/completed-courses`
    );
  }

  downloadCertificate(courseId: number): Observable<Blob> {
    return this.http.get(
      `${environment.apiUrl}/api/v1/courses/${courseId}/certificate`,
      {
        responseType: 'blob',
      }
    );
  }
}
