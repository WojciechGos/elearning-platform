import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CoursesDetailsComponent } from './courses-details.component';

describe('CoursesDetailsComponent', () => {
  let component: CoursesDetailsComponent;
  let fixture: ComponentFixture<CoursesDetailsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CoursesDetailsComponent]
    });
    fixture = TestBed.createComponent(CoursesDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
