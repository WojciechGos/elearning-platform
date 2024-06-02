import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CourseCreatorCourseInfoComponent } from './course-creator-course-info.component';

describe('CourseCreatorCourseInfoComponent', () => {
  let component: CourseCreatorCourseInfoComponent;
  let fixture: ComponentFixture<CourseCreatorCourseInfoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CourseCreatorCourseInfoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CourseCreatorCourseInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
