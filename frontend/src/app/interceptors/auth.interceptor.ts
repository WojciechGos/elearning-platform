import { Injectable } from '@angular/core';
import {
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
  HttpErrorResponse,
} from '@angular/common/http';
import { AuthService } from '../services/auth.service';
import { Observable, throwError } from 'rxjs';
import { catchError, switchMap } from 'rxjs/operators';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private authService: AuthService) { }

  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    const accessToken = this.authService.getAccessToken();

    let authReq = req;
    if (accessToken && !req.url.includes('refresh-token')) {
      if (req.headers.has('Skip-Auth')) {
        req = req.clone({
          headers: req.headers.delete('Skip-Auth'),
        });
      }
      else {
        authReq = req.clone({
          setHeaders: {
            Authorization: `Bearer ${accessToken}`,
          },
        });
      }
    }

    return next.handle(authReq).pipe(
      catchError((error: HttpErrorResponse) => {

        if (error.status === 401 && !req.url.includes('refresh-token')) {
          console.log('Attempting to refresh token...');
          return this.authService.refreshToken().pipe(
            switchMap((response) => {
              authReq = req.clone({
                setHeaders: {
                  Authorization: `Bearer ${response.accessToken}`,
                },
              });
              return next.handle(authReq);
            }),
            catchError((refreshError) => {
              console.error('Refresh Token Error:', refreshError);
              this.authService.logout();
              return throwError(refreshError);
            })
          );
        } else {
          return throwError(error);
        }
      })
    );
  }
}
