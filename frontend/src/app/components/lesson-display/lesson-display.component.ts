import { Component } from '@angular/core';
import { Input } from '@angular/core';

@Component({
  selector: 'app-lesson-display',
  templateUrl: './lesson-display.component.html',
  styleUrls: ['./lesson-display.component.css']
})
export class LessonDisplayComponent {
  @Input() title!: string;
  @Input() description!: string;
  @Input() videoUrl!: string;
  @Input() content!: string;
}
