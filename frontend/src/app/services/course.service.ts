import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Course } from '../interfaces/course.interface';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CourseService {

  constructor(
    private http: HttpClient
  ) { }

  /** GET courses from the server */
  getCourses(): Observable<Course[]> {
    return this.http.get<Course[]>(`${environment.apiUrl}/api/v1/courses`);
  }

  getCourseById(id: number): Observable<Course> { // Add this method
    return this.http.get<Course>(`${environment.apiUrl}/api/v1/courses/${id}`);
  }
}
