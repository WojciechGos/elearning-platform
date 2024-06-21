import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { of, throwError } from 'rxjs';
import { AuthService } from '../../../services/auth.service';
import { RegisterComponent } from './register.component';
import { HttpClientModule } from '@angular/common/http';

describe('RegisterComponent', () => {
  let component: RegisterComponent;
  let fixture: ComponentFixture<RegisterComponent>;
  let authServiceMock: any;

  beforeEach(async () => {
    authServiceMock = jasmine.createSpyObj('AuthService', [
      'register',
      'handleAuthCallback',
    ]);
    authServiceMock.register.and.returnValue(of({}));

    await TestBed.configureTestingModule({
      declarations: [RegisterComponent],
      imports: [ReactiveFormsModule, HttpClientModule],
      providers: [{ provide: AuthService, useValue: authServiceMock }],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should create the register form with default values', () => {
    const registerForm = component.registerForm;
    expect(registerForm).toBeTruthy();
    expect(registerForm.get('email')?.value).toBe('');
    expect(registerForm.get('password')?.value).toBe('');
    expect(registerForm.get('confirmPassword')?.value).toBe('');
    expect(registerForm.get('firstName')?.value).toBe('');
    expect(registerForm.get('lastName')?.value).toBe('');
  });

  it('should mark form as invalid if required fields are empty', () => {
    component.registerForm.controls['email'].setValue('');
    component.registerForm.controls['password'].setValue('');
    component.registerForm.controls['confirmPassword'].setValue('');
    component.registerForm.controls['firstName'].setValue('');
    component.registerForm.controls['lastName'].setValue('');
    expect(component.registerForm.valid).toBeFalsy();
  });

  it('should validate that password and confirmPassword match', () => {
    component.registerForm.controls['password'].setValue('password123');
    component.registerForm.controls['confirmPassword'].setValue('password1234');
    expect(component.registerForm.errors).toEqual({ passwordMismatch: true });
  });

  it('should set serverError on registration failure with conflict error', () => {
    authServiceMock.register.and.returnValue(
      throwError({ status: 409, error: 'Email already in use' })
    );

    component.registerForm.controls['email'].setValue('test@example.com');
    component.registerForm.controls['password'].setValue('password123');
    component.registerForm.controls['confirmPassword'].setValue('password123');
    component.registerForm.controls['firstName'].setValue('John');
    component.registerForm.controls['lastName'].setValue('Doe');
    component.onRegister();

    expect(component.serverError).toBe('Email already in use');
  });
});