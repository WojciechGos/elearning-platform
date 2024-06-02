import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CourseCreatorLessonItemComponent } from './course-creator-lesson-item.component';

describe('CourseCreatorLessonItemComponent', () => {
  let component: CourseCreatorLessonItemComponent;
  let fixture: ComponentFixture<CourseCreatorLessonItemComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CourseCreatorLessonItemComponent]
    });
    fixture = TestBed.createComponent(CourseCreatorLessonItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
