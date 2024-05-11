import { Injectable, NgZone } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Router } from '@angular/router';

interface User {
  id: number;
  currentUser: string;
  token: string;
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
    private ngZone: NgZone
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
          // store user details and jwt token in local storage to keep user logged in between page refreshes
          localStorage.setItem(
            'currentUser',
            JSON.stringify(response.currentUser)
          );
          localStorage.setItem('jwtToken', response.token);
          this.currentUserSubject.next(response.currentUser);
          this.router.navigate(['/main-page']);
          return response;
        })
      );
  }

  register(
    email: string,
    password: string,
    firstName: string,
    lastName: string
  ): Observable<User> {
    return this.http
      .post<User>(`http://localhost:8080/api/v1/auth/register`, {
        email,
        password,
        firstName,
        lastName,
      })
      .pipe(
        map((user) => {
          // store user details and jwt token in local storage to keep user logged in between page refreshes
          localStorage.setItem('currentUser', JSON.stringify(user.currentUser));
          localStorage.setItem('jwtToken', user.token);
          this.currentUserSubject.next(user);
          this.router.navigate(['/main-page']);
          return user;
        })
      );
  }

  logout(): Observable<any> {
    return this.http
      .post<any>(`http://localhost:8080/api/v1/auth/logout`, {})
      .pipe(
        map((response) => {
          console.log('LOGOUT');
          localStorage.removeItem('currentUser');
          localStorage.removeItem('jwtToken');
          this.currentUserSubject.next(null);
          this.router.navigate(['/main-page']);
          return response;
        })
      );
  }

  public getToken(): string | null {
    return localStorage.getItem('jwtToken');
  }

  loginWithGoogle(token: string): Observable<any> {
    return this.http
      .post<any>(`http://localhost:8080/api/v1/auth/google-login`, { token })
      .pipe(
        map((response) => {
          localStorage.setItem(
            'currentUser',
            JSON.stringify(response.currentUser)
          );
          localStorage.setItem('jwtToken', response.token);
          this.currentUserSubject.next(response.currentUser);
          this.ngZone.run(() => {
            this.router.navigate(['/main-page']);
          });
          return response;
        })
      );
  }
}
