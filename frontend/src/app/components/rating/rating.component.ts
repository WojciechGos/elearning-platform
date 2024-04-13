import { Component, OnInit, Input } from '@angular/core';
import {Type} from './rating.types';

@Component({
  selector: 'app-rating',
  templateUrl: './rating.component.html',
  styleUrls: ['./rating.component.css']
})
export class RatingComponent implements OnInit {

  @Input() rating!: number;
  @Input() enrollmentCount ?: number;
  @Input() type: string = Type.DEFAULT;

  constructor() {  }

  ngOnInit(): void {
  }

  getFilledStars(): any[] {
    return Array(Math.floor(this.rating));
  }

  getEmptyStars(): any[] {
    return Array(5 - Math.floor(this.rating));
  }


}
