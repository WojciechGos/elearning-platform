import { Component, OnInit, Input, isDevMode } from '@angular/core';

@Component({
  selector: 'app-courses-list-item',
  templateUrl: './courses-list-item.component.html',
  styleUrls: ['./courses-list-item.component.css']
})
export class CoursesListItemComponent implements OnInit {
  @Input() title!: string;
  @Input() image!: string;
  @Input() author!: string;
  @Input() price!: number;
  @Input() discountPrice!: number;
  @Input() rating!: number;

  ngOnInit(): void {
    if(isDevMode() == true) {
      this.image="./assets/images/course-image.png"
    }
    if(!this.image){
      this.image="./assets/images/course-image.png"
    }
  }

}
