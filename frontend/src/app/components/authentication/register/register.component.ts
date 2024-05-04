import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../../services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent {
  registerForm: FormGroup;
  serverError: string | null = null;

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

  ngOnInit(): void {}

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
