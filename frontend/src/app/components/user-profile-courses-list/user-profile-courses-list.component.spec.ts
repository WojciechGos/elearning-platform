import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserProfileCoursesListComponent } from './user-profile-courses-list.component';

describe('UserProfileCoursesListComponent', () => {
  let component: UserProfileCoursesListComponent;
  let fixture: ComponentFixture<UserProfileCoursesListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserProfileCoursesListComponent]
    });
    fixture = TestBed.createComponent(UserProfileCoursesListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
