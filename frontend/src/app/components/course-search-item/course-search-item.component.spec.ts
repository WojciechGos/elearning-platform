import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CourseSearchItemComponent } from './course-search-item.component';

describe('CourseSearchItemComponent', () => {
  let component: CourseSearchItemComponent;
  let fixture: ComponentFixture<CourseSearchItemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CourseSearchItemComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CourseSearchItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
