import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { of, throwError } from 'rxjs';
import { AuthService } from '../../../services/auth.service';
import { LoginComponent } from './login.component';
import { HttpClientModule } from '@angular/common/http';

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;
  let authServiceMock: any;

  beforeEach(async () => {
    authServiceMock = jasmine.createSpyObj('AuthService', [
      'login',
      'handleAuthCallback',
    ]);
    authServiceMock.login.and.returnValue(of({ user: 'mockUser' }));

    await TestBed.configureTestingModule({
      declarations: [LoginComponent],
      imports: [ReactiveFormsModule, HttpClientModule],
      providers: [{ provide: AuthService, useValue: authServiceMock }],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should create the login form with default values', () => {
    const loginForm = component.loginForm;
    expect(loginForm).toBeTruthy();
    expect(loginForm.get('email')?.value).toBe('');
    expect(loginForm.get('password')?.value).toBe('');
    expect(loginForm.get('rememberMe')?.value).toBe(false);
  });

  it('should mark form as invalid if required fields are empty', () => {
    component.loginForm.controls['email'].setValue('');
    component.loginForm.controls['password'].setValue('');
    expect(component.loginForm.valid).toBeFalsy();
  });

  it('should set serverError on login failure', () => {
    authServiceMock.login.and.returnValue(
      throwError({ message: 'Invalid email or password' })
    );

    component.loginForm.controls['email'].setValue('test@example.com');
    component.loginForm.controls['password'].setValue('password123');
    component.onLogin();

    expect(component.serverError).toBe('Invalid email or password');
  });

  it('should clear serverError on login success', () => {
    component.loginForm.controls['email'].setValue('test@example.com');
    component.loginForm.controls['password'].setValue('password123');
    component.onLogin();

    expect(component.serverError).toBeNull();
  });
});