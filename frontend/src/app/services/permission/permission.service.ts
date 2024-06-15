import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
@Injectable({
  providedIn: 'root'
})
export class PermissionService {

  constructor(
    private http: HttpClient
  ) { }

  checkUserPermission(role: string): Observable<boolean> {
    return this.http.get<boolean>(`${environment.apiUrl}/api/v1/permissions/roles/${role}/me`);
  }
}
