import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CoursesDetailsComponent } from './courses-details.component';
import { HttpClientModule } from '@angular/common/http';
import { RouterTestingModule } from '@angular/router/testing';
import { provideMockStore } from '@ngrx/store/testing';
import { StoreModule } from '@ngrx/store';

describe('CoursesDetailsComponent', () => {
  let component: CoursesDetailsComponent;
  let fixture: ComponentFixture<CoursesDetailsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CoursesDetailsComponent],
      imports: [
        HttpClientModule,
        RouterTestingModule,
        StoreModule.forRoot(provideMockStore)
      ]
    });
    fixture = TestBed.createComponent(CoursesDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
