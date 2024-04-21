import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-logged-in-page',
  templateUrl: './logged-in-page.component.html',
})
export class LoggedInPageComponent {
  currentUser: string;

  constructor() {
    this.currentUser = localStorage.getItem('currentUser') || 'Guest';
  }

  logout() {
    localStorage.removeItem('currentUser');
    localStorage.removeItem('jwtToken');
    window.location.href = '/login'; // Przekierowanie na stronę logowania
  }
}
