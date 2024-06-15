import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserProfileCompletedCoursesListComponent } from './user-profile-completed-courses-list.component';

describe('UserProfileCompletedCoursesListComponent', () => {
  let component: UserProfileCompletedCoursesListComponent;
  let fixture: ComponentFixture<UserProfileCompletedCoursesListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserProfileCompletedCoursesListComponent]
    });
    fixture = TestBed.createComponent(UserProfileCompletedCoursesListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
