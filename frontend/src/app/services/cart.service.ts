import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { switchMap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  private apiUrl = 'http://localhost:8080/api/v1';
  currentUser: string;

  constructor(
    private http: HttpClient
  ) { 
    this.currentUser = JSON.parse(localStorage.getItem('currentUser') || '{}')
  }

  addToCart(courseId: number, cart: any): void {
      let cartId = cart ? cart.id : 20;
      let cartItemRequest = { courseId: courseId, cartId: cartId, email: this.currentUser };
      this.http.post<any>(`${this.apiUrl}/cartItems`, cartItemRequest);
  }

  getCart(): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/carts/user/` + this.currentUser)
  }
}
