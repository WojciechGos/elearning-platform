import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Notification } from 'src/app/interfaces/notification.interface';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  constructor(
    private http: HttpClient
  ) { }

  getUsersNotifications() : Observable<Notification[]> {
    return this.http.get<Notification[]>(`${environment.apiUrl}/api/v1/notifications/me`);
  }

  getUsersNotificationsByStatus(status: string) : Observable<Notification[]> {
    return this.http.get<Notification[]>(`${environment.apiUrl}/api/v1/notifications/${status}/me`);
  }

  updateNotificationStatus(notificationId: number, status: string) : Observable<Notification> {
    return this.http.put<Notification>(`${environment.apiUrl}/api/v1/notifications/${notificationId}`, {notificationStatus: status});
  }
}
