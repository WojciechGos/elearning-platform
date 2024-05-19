import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css'],
})
export class UserProfileComponent implements OnInit {
  user: any;

  constructor(private authService: AuthService) {}

  ngOnInit(): void {
    this.authService.currentUser.subscribe((user) => {
      this.user = user;
    });
    console.log(this.user);
  }

  logout(): void {
    this.authService.logout().subscribe(
      (response) => {
        console.log('Logged out');
      },
      (error) => {
        console.error('Logout failed', error);
      }
    );
  }
}
