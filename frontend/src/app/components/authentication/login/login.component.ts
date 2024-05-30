import { Component, OnInit, AfterViewInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../../services/auth.service';

declare const google: any;

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements AfterViewInit {
  loginForm: FormGroup;
  serverError: string | null = null;
  googleClientId: string | null = null;

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

  ngAfterViewInit(): void {
    this.authService.getGoogleClientId().subscribe({
      next: (clientId) => {
        this.googleClientId = clientId;
        google.accounts.id.initialize({
          client_id: this.googleClientId,
          callback: (response: any) => {
            this.authService.loginWithGoogle(response.credential).subscribe({
              next: (user) => {
                console.log('Login successful', user);
                this.serverError = null;
              },
              error: (error) => {
                console.error('Login failed', error);
                this.serverError = 'Google login failed';
              },
            });
          },
        });

        google.accounts.id.renderButton(document.getElementById('googleBtn'), {
          type: 'standard',
          theme: 'filled_blue',
          size: 'large',
          shape: 'rectangle',
          width: 400,
        });
      },
      error: (error) => {
        console.error('Failed to fetch Google Client ID', error);
      },
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
