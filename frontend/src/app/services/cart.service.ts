import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { switchMap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class CartService {
  private apiUrl = 'http://localhost:8080/api/v1';
  currentUser: string;

  constructor(private http: HttpClient) {
    this.currentUser = JSON.parse(localStorage.getItem('currentUser') || '{}');
  }

  addCartItem(courseId: number): Observable<any> {
    this.currentUser = JSON.parse(localStorage.getItem('currentUser') || '{}');
    let cartItemRequest = { courseId: courseId, email: this.currentUser };
    return this.http.post<any>(`${this.apiUrl}/cartItems`, cartItemRequest);
  }

  getCart(): Observable<any> {
    this.currentUser = JSON.parse(localStorage.getItem('currentUser') || '{}');
    console.log(this.currentUser);
    return this.http.get<any>(
      `${this.apiUrl}/carts/pending/` + this.currentUser
    );
  }

  deleteCartItem(cartItemId: number): Observable<any> {
    return this.http.delete<any>(`${this.apiUrl}/cartItems/${cartItemId}`);
  }

  getAllCartsByEmail(email: String): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/carts/user/` + email);
  }

  getCartByCartID(id: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/carts/${id}`);
  }

  updateCartStatus(id: number, status: string): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/carts/${id}`, {
      cartStatus: status,
    });
  }
}
