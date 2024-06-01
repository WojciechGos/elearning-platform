import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Category } from '../../interfaces/category.interface';
import { environment } from 'src/environments/environment';
@Injectable({
  providedIn: 'root'
})
export class CategoryService {


  constructor(
    private http: HttpClient
  ) { }

  getCategories(): Observable<Category[]> {
    return this.http.get<Category[]>(environment.apiUrl + '/api/v1/categories')
  }

  addCategoryToCourse(categoryId: number, courseId: number): Observable<any> {
    return this.http.post(`${environment.apiUrl}/api/v1/courses/${courseId}/categories/${categoryId}`, {})
  }

  removeCategoryFromCourse(categoryId: number, courseId: number): Observable<any> {
    return this.http.delete(`${environment.apiUrl}/api/v1/courses/${courseId}/categories/${categoryId}`)
  }


}
