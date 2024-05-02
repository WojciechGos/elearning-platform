import { Component, OnInit } from '@angular/core';
import { Category } from 'src/app/interfaces/category.interface';
import { CategoryService } from 'src/app/services/category.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  isListVisible = false;
  categories: Category[] = [];
  searchInput: string = '';

  constructor(
    private router: Router,
    private categoryService: CategoryService
  ) { }

  ngOnInit(): void {
  }

  onUserAuthenticate(): void{
    
  }
  onSearchHandler(): void{
    this.router.navigate(['/course-search'], { queryParams: { keyword: this.searchInput } });
  }

  showList() {
    this.isListVisible = true;
    if(this.categories.length === 0) {
      this.categoryService.getCategories().subscribe((categories) => {
        this.categories = categories;
      });
    }
  }

  hideList() {
    this.isListVisible = false;
  }

}
