import { Component, OnInit } from '@angular/core';
import { CategoryService } from 'src/app/services/category.service';
import { Category } from 'src/app/interfaces/category.interface';
import { MatListOption } from '@angular/material/list'; // Add this line

@Component({
  selector: 'app-course-search',
  templateUrl: './course-search.component.html',
  styleUrls: ['./course-search.component.css']
})
export class CourseSearchComponent implements OnInit {
  categories: Category[] = [];

  keyword: string = '';
  selectedCategories: string[] = [];
  selectedLanguages: string[] = [];
  selectedDifficulty: string[] = [];
  minPrice: number = 0;
  maxPrice: number = 1000;
  minRating: number = 0;

  constructor(private categoryService: CategoryService) { }

  ngOnInit(): void {
    this.categoryService.getCategories().subscribe((categories) => {
      this.categories = categories;
    });
  }
  onCategoriesSelectionChange(selectedOptions: MatListOption[]): void {
    this.selectedCategories = selectedOptions.map(o => o.value);
  }

  onFindButtonClick(): void {
    console.log(this.keyword);
    console.log(this.selectedCategories);
  }

}
