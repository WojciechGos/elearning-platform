import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, forkJoin, of } from 'rxjs';
import { map, switchMap } from 'rxjs/operators';
import { CartItem } from '../../interfaces/cartItem.interface';

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
    if(localStorage.getItem('cartId') ) { 
      return this.getCartByCartID(Number(localStorage.getItem('cartId')));
    } else {
      return this.http.get<any>(`${this.apiUrl}/carts/pending`);
    }
  }

  deleteCartItem(cartItemId: number): Observable<any> {
    return this.http.delete<any>(`${this.apiUrl}/cartItems/${cartItemId}`);
  }

  getCartsByUser(email: string): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/carts/user/${email}`);
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
        const existingCourseIds = cart.items.map((item: any) => item.course.id);
        
        const newItems = items.filter((item: any) => !existingCourseIds.includes(item.course.id));
        
        if (newItems.length > 0) {
          return forkJoin(newItems.map((item: any) => this.addCartItem(item.course.id)));
        } else {
          return of(null);
        }
      })
    );
  }

  getPendingCartsByUser(email: string): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/carts/user/${email}/pending`);
  }
  
}
