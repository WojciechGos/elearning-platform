import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from './components/authentication/login/login.component';
import {RegisterComponent} from './components/authentication/register/register.component';
import {LoggedInPageComponent} from './logged-in-page/logged-in-page.component';
import {AuthGuard} from './guards/auth.guard';
import {CourseDetailsComponent} from './components/course-details/course-details.component';
import {MainPageComponent} from './components/main-page/main-page.component';
import {CancelComponent} from './components/payment/cancel/cancel.component';
import {SuccessComponent} from './components/payment/success/success.component';
import {CartComponent} from './components/cart/cart.component';
import {UsersComponent} from './components/admin/users/users.component';
import {CartDetailsComponent} from './components/admin/cart-details/cart-details.component';
import {CourseSearchComponent} from './components/course-search/course-search.component';
import {UserCartsComponent} from './components/admin/user-carts/user-carts.component';
import {CourseCreatorComponent} from './components/course-creator/course-creator.component';
import {UserProfileComponent} from './components/user-profile/user-profile.component';
import {CourseDisplayComponent} from './components/course-display/course-display.component';

const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'course-details/:id', component: CourseDetailsComponent},
  {path: 'register', component: RegisterComponent},
  // {
  //   path: 'logged-in',
  //   component: LoggedInPageComponent,
  //   canActivate: [AuthGuard],
  // },
  {path: 'main-page', component: MainPageComponent},
  {path: '', redirectTo: '/main-page', pathMatch: 'full'},
  {path: 'cancel', component: CancelComponent},
  {path: 'success', component: SuccessComponent},
  {path: 'cart', component: CartComponent},
  {path: 'users', component: UsersComponent},
  {path: 'cart-details/:id/:email', component: CartDetailsComponent},
  {path: 'course-search', component: CourseSearchComponent},
  {path: 'user-carts/:email', component: UserCartsComponent},
  {
    path: 'user-profile',
    component: UserProfileComponent,
    canActivate: [AuthGuard],
  },
  {path: 'course-display/:id', component: CourseDisplayComponent},
  {path: 'course-display/:id/:lessonId', component: CourseDisplayComponent},
  {path: 'course-creator', component: CourseCreatorComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {
}
