import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CourseDisplayComponent } from './course-display.component';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientModule } from '@angular/common/http';

describe('CourseDisplayComponent', () => {
  let component: CourseDisplayComponent;
  let fixture: ComponentFixture<CourseDisplayComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CourseDisplayComponent],
      imports: [
        RouterTestingModule,
        HttpClientModule
      ]
    });
    fixture = TestBed.createComponent(CourseDisplayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
