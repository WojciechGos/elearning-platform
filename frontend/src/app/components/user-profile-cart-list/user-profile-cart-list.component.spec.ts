import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserProfileCartListComponent } from './user-profile-cart-list.component';

describe('UserProfileCartListComponent', () => {
  let component: UserProfileCartListComponent;
  let fixture: ComponentFixture<UserProfileCartListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserProfileCartListComponent]
    });
    fixture = TestBed.createComponent(UserProfileCartListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
