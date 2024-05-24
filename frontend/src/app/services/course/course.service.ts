import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, } from 'rxjs';
import { Course } from '../../interfaces/course.interface';
import { environment } from 'src/environments/environment';
import { CourseFilter } from '../../interfaces/courseFilter.interface';

@Injectable({
  providedIn: 'root'
})
export class CourseService {

  constructor(
    private http: HttpClient
  ) { }

  getCoursesByFilter(params?: any): Observable<CourseFilter> {
    return this.http.get<CourseFilter>(`${environment.apiUrl}/api/v1/courses`, { params });
  }


  getCourses(): Observable<Course[]> {
    return this.http.get<Course[]>(`${environment.apiUrl}/api/v1/courses`);
  }

  getCourseById(id: number): Observable<Course> { 
    return this.http.get<Course>(`${environment.apiUrl}/api/v1/courses/${id}`);
  }

  createCourse(course: Course): Observable<Course> {
    return this.http.post<Course>(`${environment.apiUrl}/api/v1/courses`, course);
  }

  updateCourse(id: number, course: Course): Observable<Course> {
    return this.http.put<Course>(`${environment.apiUrl}/api/v1/courses/${id}`, course);
  }
}
