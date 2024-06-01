import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../../services/auth.service';

declare const google: any;

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  serverError: string | null = null;

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService
  ) {
    this.loginForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]],
      rememberMe: [false],
    });
  }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]],
      rememberMe: [false],
    });
  }

  ngAfterViewInit(): void {
    console.log('google init');
    google.accounts.id.renderButton(document.getElementById('googleBtn'), {
      type: 'standard',
      theme: 'filled_blue',
      size: 'large',
      shape: 'rectangle',
      width: 400,
    });
  }

  onLogin(): void {
    if (this.loginForm.valid) {
      const { email, password } = this.loginForm.value;
      this.authService.login(email, password).subscribe({
        next: (user) => {
          console.log('Login successful', user);
          this.serverError = null;
        },
        error: (error) => {
          console.error('Login failed', error);
          this.serverError = 'Invalid email or password';
        },
      });
    } else {
      console.log('Form is not valid');
    }
  }
}
