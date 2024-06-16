import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserProfileCoursesListComponent } from './user-profile-courses-list.component';
import { HttpClientModule } from '@angular/common/http';
import { StoreModule } from '@ngrx/store';
import { provideMockStore } from '@ngrx/store/testing';

describe('UserProfileCoursesListComponent', () => {
  let component: UserProfileCoursesListComponent;
  let fixture: ComponentFixture<UserProfileCoursesListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserProfileCoursesListComponent],
      imports: [
        HttpClientModule,
        StoreModule.forRoot(provideMockStore)
      ]
    });
    fixture = TestBed.createComponent(UserProfileCoursesListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
