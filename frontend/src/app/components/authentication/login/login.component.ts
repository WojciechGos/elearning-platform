import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../../services/auth.service';
import { environment } from 'src/environments/environment';

declare const google: any;

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  serverError: string | null = null;
  auth2: any;

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
    google.accounts.id.initialize({
      client_id:
        '659439241514-h8n75fq8ospqergqnuf67na0b27fec5k.apps.googleusercontent.com',
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
