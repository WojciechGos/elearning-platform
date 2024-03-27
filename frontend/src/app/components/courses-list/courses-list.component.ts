import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-courses-list',
  templateUrl: './courses-list.component.html',
  styleUrls: ['./courses-list.component.css']
})
export class CoursesListComponent implements OnInit {
  courses = [
    { title: 'Python od podstaw', image: 'https://via.placeholder.com/150', author: 'John Doe', price:  39.99},
    { title: 'GIT dla zaawansowanych', image: 'https://via.placeholder.com/150', author: 'Ann Smith', price:  49.99},
    { title: 'JavaScript dla początkujących', image: 'https://via.placeholder.com/150', author: 'Emily Johnson', price: 29.99 },
    { title: 'Algorytmy i struktury danych', image: 'https://via.placeholder.com/150', author: 'Michael Brown', price: 59.99 },
    { title: 'Machine Learning w praktyce', image: 'https://via.placeholder.com/150', author: 'Sophia Lee', price: 79.99 }
  ];

  constructor() { }

  ngOnInit(): void {
  }

}
