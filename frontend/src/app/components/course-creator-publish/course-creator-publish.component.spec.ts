import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CourseCreatorPublishComponent } from './course-creator-publish.component';

describe('CourseCreatorPublishComponent', () => {
  let component: CourseCreatorPublishComponent;
  let fixture: ComponentFixture<CourseCreatorPublishComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CourseCreatorPublishComponent]
    });
    fixture = TestBed.createComponent(CourseCreatorPublishComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
