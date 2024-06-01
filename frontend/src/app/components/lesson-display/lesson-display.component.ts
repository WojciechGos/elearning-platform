import { Component, ChangeDetectorRef, OnInit } from '@angular/core';
import { Input } from '@angular/core';

@Component({
  selector: 'app-lesson-display',
  templateUrl: './lesson-display.component.html',
  styleUrls: ['./lesson-display.component.css']
})
export class LessonDisplayComponent implements OnInit {
  @Input() title!: string;
  @Input() description!: string;
  @Input() videoUrl!: string;

  constructor(private cdr: ChangeDetectorRef) {}

  ngOnInit(): void {
    console.log('lesson ngOnInit');
  }

  reRenderComponent() {
    console.log('re-rendering component');
    this.cdr.detectChanges();
  }
}