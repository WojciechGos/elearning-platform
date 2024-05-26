import { Component, ElementRef, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../../services/auth.service';
import { environment } from 'src/environments/environment';

declare const google: any;

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent {
  @ViewChild('googleBtn') googleBtn: ElementRef | undefined;

  registerForm: FormGroup;
  serverError: string | null = null;
  auth2: any;

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService
  ) {
    this.registerForm = this.formBuilder.group(
      {
        email: ['', [Validators.required, Validators.email]],
        password: ['', [Validators.required, Validators.minLength(6)]],
        confirmPassword: [''],
        firstName: ['', [Validators.required]],
        lastName: ['', [Validators.required]],
      },
      { validators: this.matchPassword }
    );
  }

  ngAfterViewInit(): void {
    google.accounts.id.initialize({
      client_id: environment.googleClientId,
      callback: (response: any) => {
        console.log('Google sign-in response:', response);
        this.authService
          .loginWithGoogle(response.credential)
          .subscribe((user) => {
            console.log('Login successful', user);
            this.serverError = null;
          });
      },
    });

    console.log(document.getElementById('googleBtn'));

    google.accounts.id.renderButton(document.getElementById('googleBtn'), {
      type: 'standard',
      theme: 'filled_blue',
      size: 'large',
      shape: 'rectangle',
      width: 400,
    });
  }

  onRegister(): void {
    if (this.registerForm.valid) {
      const { email, password, firstName, lastName } = this.registerForm.value;
      this.authService
        .register(email, password, firstName, lastName)
        .subscribe({
          next: (response) => {
            console.log('Registration successful', response);
            this.serverError = null;
          },
          error: (error) => {
            console.error('Registration failed', error);
            if (error.status === 409) {
              this.serverError = error.error;
            } else {
              alert('Registration failed. Please try again later.');
            }
          },
        });
    } else {
      console.log('Form is not valid');
    }
  }

  private matchPassword(group: FormGroup): { [key: string]: any } | null {
    let pass = group.get('password')?.value;
    let confirmPass = group.get('confirmPassword')?.value;
    return pass === confirmPass ? null : { passwordMismatch: true };
  }
}
