import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent implements OnInit {
  registerForm!: FormGroup;

  constructor(private formBuilder: FormBuilder, private authService: AuthService) {}

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      confirmPassword: ['', Validators.required], 
    });
  }

  onRegister() {
    if (this.registerForm.valid) {
      console.log(this.registerForm.value);
      this.authService.register(this.registerForm.value.email, this.registerForm.value.password)
    .subscribe({
      next: (user) => {
        console.log(user);
      },
      error: (error) => {
        console.error(error);
      }
    });
    }
  }
}
