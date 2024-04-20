import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/authentication/login/login.component';
import { RegisterComponent } from './components/authentication/register/register.component';
import { CourseDetailsComponent } from './components/course-details/course-details.component';
import { MainPageComponent } from './components/main-page/main-page.component';
import { CheckoutComponent } from './components/payment/checkout/checkout.component';
import { CancelComponent } from './components/payment/cancel/cancel.component';
import { SuccessComponent } from './components/payment/success/success.component';
import { CartComponent } from './components/cart/cart.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'course-details/:id', component: CourseDetailsComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'main-page', component: MainPageComponent},
  { path: '', redirectTo: '/main-page', pathMatch: 'full' }, 
  { path: 'checkout', component: CheckoutComponent},
  { path: 'cancel', component: CancelComponent},
  { path: 'success', component: SuccessComponent},
  { path: 'cart', component: CartComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
