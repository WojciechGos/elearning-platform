import { Component, OnInit } from '@angular/core';
import { CategoryService } from 'src/app/services/category.service';
import { Category } from 'src/app/interfaces/category.interface';
import { PageEvent } from '@angular/material/paginator';
import { Course } from 'src/app/interfaces/course.interface';
import { CourseService } from 'src/app/services/course.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-course-search',
  templateUrl: './course-search.component.html',
  styleUrls: ['./course-search.component.css']
})
export class CourseSearchComponent implements OnInit {
  difficultyLevels = ['Beginner', 'Intermediate', 'Advanced'];
  languages = ['Polish', 'English', 'Spanish'];

  length: number = 0;
  pageSize: number = 5; 
  currentPage: number = 0;
  categories: Category[] = [];
  keyword: string = '';
  selectedCategories: string[] = [];
  selectedLanguages: string[] = [];
  selectedLevels: string[] = [];
  minPrice: number = 0;
  maxPrice: number = 1000;
  minRating: number = 0;
  courses: Course[] = [];

  constructor(
    private categoryService: CategoryService,
    private courseService: CourseService,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    // get available categories to display it in the filter
    this.categoryService.getCategories().subscribe((categories) => {
      this.categories = categories;
    });

    // get query params from the url
    let selectedCategories = this.route.snapshot.queryParamMap.get('categories');
    let selectedKeyword = this.route.snapshot.queryParamMap.get('keyword');
    let params = {

    }
    // get all courses to display it in the course list
    this.courseService.getCoursesByFilter().subscribe((courseFilter) => {
      this.length = courseFilter.count;
      this.courses = courseFilter.courses;
    });
  }

  handlePageEvent(event: PageEvent) {
    this.currentPage = event.pageIndex;
    this.pageSize = event.pageSize;     
    let params = {
      keyword: this.keyword,
      categories: this.selectedCategories,
      languages: this.selectedLanguages,
      levels: this.selectedLevels,
      minPrice: this.minPrice,
      maxPrice: this.maxPrice,
      minRating: this.minRating,
      page: this.currentPage,
    };
    this.courseService.getCoursesByFilter(params).subscribe((courseFilter) => {
      this.courses = courseFilter.courses;
      this.length = courseFilter.count;
    });
  }

  onFindButtonClick(): void {
    let params = {
      keyword: this.keyword,
      categories: this.selectedCategories,
      languages: this.selectedLanguages,
      levels: this.selectedLevels,
      minPrice: this.minPrice,
      maxPrice: this.maxPrice,
      minRating: this.minRating
    };
    this.courseService.getCoursesByFilter(params).subscribe((courseFilter) => {
      this.courses = courseFilter.courses;
      this.length = courseFilter.count;
      console.log(this.length);
      console.log(this.courses);
    });
  }

}
