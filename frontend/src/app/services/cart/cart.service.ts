import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, forkJoin, of, catchError } from 'rxjs';
import { map, switchMap } from 'rxjs/operators';
import { CartItem } from '../../interfaces/cartItem.interface';
import { Course } from 'src/app/interfaces/course.interface';

@Injectable({
  providedIn: 'root',
})
export class CartService {
  private apiUrl = 'http://localhost:8080/api/v1';

  constructor(private http: HttpClient) {
  }

  addCartItem(courseId: number): Observable<any> {
    let cartItemRequest;
    if(localStorage.getItem('cartId')) {
      cartItemRequest = { courseId: courseId, cartId: localStorage.getItem('cartId')};
    } else {
      cartItemRequest = { courseId: courseId };
    }
    
    return this.http.post<any>(`${this.apiUrl}/cartItems`, cartItemRequest);
  }

  getCart(): Observable<any> {
    if (localStorage.getItem('cartId')) {
      return this.getCartByCartID(Number(localStorage.getItem('cartId')));
    } else if(localStorage.getItem('currentUser')) {
      return this.http.get<any>(`${this.apiUrl}/carts/pending`).pipe(
        catchError(() => {
          return of({ items: [] });
        })
      );
    } else {
      return of({ items: [] });
    }
  }

  deleteCartItem(cartItemId: number): Observable<any> {
    return this.http.delete<any>(`${this.apiUrl}/cartItems/${cartItemId}`);
  }

  getCartsByUser(email: string): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/carts/user/${email}`);
  }

  getUsersCartByStatus(status: string): Observable<Course[]> {
    return this.http.get<Course[]>(`${this.apiUrl}/carts/status/${status}/me`);
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
        console.log(cart)
        if (cart && cart.items) {
          return cart.items.some((item: CartItem) => item.course.id === courseId);
        }
        return false;
      })
    );
  }

  handleLoggedInUser(): void {
    const cartId = Number(localStorage.getItem('cartId'));
    if (cartId) {
      this.getCartByCartID(cartId).subscribe((cart) => {
        this.addItemsToLoggedInUserCart(cart.items).subscribe(
          (error) => {
            console.log("Error:", error);
          }
        );
      });
    }
  }

  private addItemsToLoggedInUserCart(items: any[]): Observable<any> {
    localStorage.removeItem('cartId');
  
    return this.getCart().pipe(
      switchMap((cart) => {
        if (!cart || Object.keys(cart).length === 0) {
          return forkJoin(items.map((item: any) => this.addCartItem(item.course.id)));
        } else {
          console.log('Items in cart');
          const existingCourseIds = cart.items ? cart.items.map((item: any) => item.course.id) : [];
          const newItems = items.filter((item: any) => !existingCourseIds.includes(item.course.id));
  
          if (newItems.length > 0) {
            return forkJoin(newItems.map((item: any) => this.addCartItem(item.course.id)));
          } else {
            return of(null);
          }
        }
      })
    );
  }

  getPendingCartsByUser(email: string): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/carts/user/${email}/pending`);
  }
  
}
