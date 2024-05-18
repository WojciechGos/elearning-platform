import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CourseCreatorLessonComponent } from './course-creator-lesson.component';

describe('CourseCreatorLessonComponent', () => {
  let component: CourseCreatorLessonComponent;
  let fixture: ComponentFixture<CourseCreatorLessonComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CourseCreatorLessonComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CourseCreatorLessonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
