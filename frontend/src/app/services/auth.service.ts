import { Injectable, NgZone } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { Router } from '@angular/router';
import { CartService } from './cart.service';

interface User {
  email: string;
  firstName: string;
  lastName: string;
}

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private currentUserSubject: BehaviorSubject<User | null>;
  public currentUser: Observable<User | null>;

  constructor(
    private http: HttpClient,
    private router: Router,
    private ngZone: NgZone,
    private cartService: CartService
  ) {
    const storedUser = localStorage.getItem('currentUser');
    const user = storedUser ? JSON.parse(storedUser) : null;
    this.currentUserSubject = new BehaviorSubject<User | null>(user);
    this.currentUser = this.currentUserSubject.asObservable();
  }

  public get currentUserValue(): User | null {
    return this.currentUserSubject.value;
  }

  login(email: string, password: string): Observable<any> {
    return this.http
      .post<any>(`http://localhost:8080/api/v1/auth/login`, { email, password })
      .pipe(
        map((response) => {
          this.storeUserCredentials(response);
          return response;
        })
      );
  }

  register(
    email: string,
    password: string,
    firstName: string,
    lastName: string
  ): Observable<any> {
    return this.http
      .post<any>(`http://localhost:8080/api/v1/auth/register`, {
        email,
        password,
        firstName,
        lastName,
      })
      .pipe(
        map((response) => {
          this.storeUserCredentials(response);
          return response;
        })
      );
  }

  private storeUserCredentials(response: any) {
    const user = response.user;
    localStorage.setItem('currentUser', JSON.stringify(user));
    localStorage.setItem('accessToken', response.accessToken);
    localStorage.setItem('refreshToken', response.refreshToken);
    this.currentUserSubject.next(user);
    this.router.navigate(['/main-page']);
    this.cartService.handleLoggedInUser();
  }

  logout(): Observable<any> {
    return this.http
      .post<any>(`http://localhost:8080/api/v1/auth/logout`, {})
      .pipe(
        map((response) => {
          this.clearUserCredentials();
          return response;
        })
      );
  }

  private clearUserCredentials() {
    localStorage.removeItem('currentUser');
    localStorage.removeItem('accessToken');
    localStorage.removeItem('refreshToken');
    this.currentUserSubject.next(null);
    this.router.navigate(['/main-page']);
  }

  public getAccessToken(): string | null {
    return localStorage.getItem('accessToken');
  }

  public getRefreshToken(): string | null {
    return localStorage.getItem('refreshToken');
  }

  public refreshToken(): Observable<any> {
    const refreshToken = this.getRefreshToken();
    console.log('Attempting to refresh token:', refreshToken);

    return this.http
      .post<any>(`http://localhost:8080/api/v1/auth/refresh-token`, {
        refreshToken,
      })
      .pipe(
        map((response) => {
          console.log('Token refreshed:', response.accessToken);
          localStorage.setItem('accessToken', response.accessToken);
          return response;
        }),
        catchError((error) => {
          console.error('Refresh token failed:', error);
          return throwError(error);
        })
      );
  }

  loginWithGoogle() {
    window.location.href = 'http://localhost:8080/api/v1/auth/google-login';
  }

  getGoogleClientId(): Observable<string> {
    return this.http
      .get<{ googleClientId: string }>(
        `http://localhost:8080/api/v1/auth/google-client-id`
      )
      .pipe(
        map((response) => response.googleClientId),
        catchError((error) => {
          console.error('Failed to fetch Google Client ID', error);
          return throwError(error);
        })
      );
  }
}
