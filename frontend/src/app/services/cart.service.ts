import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { CartItem } from '../interfaces/cartItem.interface';

@Injectable({
  providedIn: 'root',
})
export class CartService {
  private apiUrl = 'http://localhost:8080/api/v1';

  constructor(private http: HttpClient) {
  }

  addCartItem(courseId: number): Observable<any> {
    let cartItemRequest = { courseId: courseId };
    return this.http.post<any>(`${this.apiUrl}/cartItems`, cartItemRequest);
  }

  getCart(): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/carts/pending`);
  }

  deleteCartItem(cartItemId: number): Observable<any> {
    return this.http.delete<any>(`${this.apiUrl}/cartItems/${cartItemId}`);
  }

  getAllCartsByUser(): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/carts/user`);
  }

  getCartByCartID(id: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/carts/${id}`);
  }

  updateCartStatus(id: number, status: string): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/carts/${id}`, {
      cartStatus: status,
    });
  }

  isCourseInCart(courseId: number): Observable<boolean> {
    return this.getCart().pipe(
      map(cart => {
        if (cart && cart.items) {
          return cart.items.some((item: CartItem) => item.course.id === courseId);
        }
        return false;
      })
    );
  }

}
