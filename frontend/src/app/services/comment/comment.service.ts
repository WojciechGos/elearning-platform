import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Comment } from 'src/app/interfaces/comment.interface';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  constructor(private http: HttpClient) { }

  getCommentsByCourseId(courseId: number): Observable<Comment[]> {
    return this.http.get<Comment[]>(`${environment.apiUrl}/api/v1/comments/course/${courseId}`);
  }

  addComment(comment: { content: string; courseId: number; }): Observable<Comment> {
    return this.http.post<Comment>(`${environment.apiUrl}/api/v1/comments`, comment);
  }

}
