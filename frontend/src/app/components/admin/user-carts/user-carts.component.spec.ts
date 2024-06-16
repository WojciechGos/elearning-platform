import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { UserCartsComponent } from './user-carts.component';
import { HttpClientModule } from '@angular/common/http';

describe('UserCartsComponent', () => {
  let component: UserCartsComponent;
  let fixture: ComponentFixture<UserCartsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserCartsComponent ],
      imports: [
         RouterTestingModule,
         HttpClientModule
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UserCartsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
