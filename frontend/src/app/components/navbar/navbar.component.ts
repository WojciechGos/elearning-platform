import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { NotificationService } from 'src/app/services/notification/notification.service';
import { Notification } from 'src/app/interfaces/notification.interface';
@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
})
export class NavbarComponent implements OnInit {
  searchInput: string = '';
  isLoggedIn: boolean = false;
  userInitials: string = '';
  notificationList: Notification[] = [];

  @Output() searchEvent = new EventEmitter<string>();

  constructor(
    private router: Router,
    private authService: AuthService,
    private notificationService: NotificationService,
  ) {}

  ngOnInit(): void {
    this.authService.currentUser.subscribe((user) => {
      this.isLoggedIn = !!user;
      if (user) {
        this.userInitials = this.getUserInitials(user.firstName, user.lastName);
      }
    });
    this.notificationService.getUsersNotificationsByStatus('UNREAD').subscribe((response)=>{
      console.log("Notifications")
      console.log(response);
      this.notificationList = response;
    });
  }

  onSearchHandler(): void {
    this.searchEvent.emit(this.searchInput);
    this.router.navigate(['/course-search'], {
      queryParams: { keyword: this.searchInput },
    });
  }


  getUserInitials(firstName: string, lastName: string): string {
    if (!firstName || !lastName) return '';
    return `${firstName.charAt(0)}${lastName.charAt(0)}`.toUpperCase();
  }


  navigateToUserProfile(): void {
    this.router.navigate(['/user-profile']);
  }
}
