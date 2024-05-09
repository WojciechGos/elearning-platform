import { Component, OnInit, Input } from '@angular/core';
import { Lesson } from 'src/app/interfaces/lesson.interface';

@Component({
  selector: 'app-lesson-list',
  templateUrl: './lesson-list.component.html',
  styleUrls: ['./lesson-list.component.css']
})
export class LessonListComponent implements OnInit {

  @Input() lessons!: Lesson[];
  constructor() { }

  ngOnInit(): void {
  }

}
