import { Component, OnInit } from '@angular/core';
import { Category } from 'src/app/interfaces/category.interface';
import { CategoryService } from 'src/app/services/category/category.service';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
})
export class NavbarComponent implements OnInit {
  isListVisible = false;
  categories: Category[] = [];
  searchInput: string = '';
  isLoggedIn: boolean = false;

  constructor(
    private router: Router,
    private categoryService: CategoryService,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.authService.currentUser.subscribe((user) => {
      this.isLoggedIn = !!user;
    });
  }

  onSearchHandler(): void {
    this.router.navigate(['/course-search'], {
      queryParams: { keyword: this.searchInput },
    });
  }

  showList() {
    this.isListVisible = true;
    if (this.categories.length === 0) {
      this.categoryService.getCategories().subscribe((categories) => {
        this.categories = categories;
      });
    }
  }

  hideList() {
    this.isListVisible = false;
  }

  logout(): void {
    this.authService.logout().subscribe(
      (response) => {
        console.log('Logged out');
        this.router.navigate(['/main-page']);
      },
      (error) => {
        console.error('Logout failed', error);
      }
    );
  }
}
