import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-course-search-item',
  templateUrl: './course-search-item.component.html',
  styleUrls: ['./course-search-item.component.css']
})
export class CourseSearchItemComponent implements OnInit {
  @Input() title!: string;
  @Input() image!: string;
  @Input() author!: string;
  @Input() price!: number;
  @Input() discountPrice!: number;
  @Input() rating!: number;
  constructor() { }

  ngOnInit(): void {
  }

}
