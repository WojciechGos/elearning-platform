import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CourseMiniatureComponent } from './course-miniature.component';

describe('CourseMiniatureComponent', () => {
  let component: CourseMiniatureComponent;
  let fixture: ComponentFixture<CourseMiniatureComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CourseMiniatureComponent]
    });
    fixture = TestBed.createComponent(CourseMiniatureComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
