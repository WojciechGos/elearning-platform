import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from './components/authentication/login/login.component';
import {RegisterComponent} from './components/authentication/register/register.component';
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
import { AdminDashboardComponent } from './components/admin/admin-dashboard/admin-dashboard.component';
import { CoursesComponent } from './components/admin/courses/courses.component';
import { CoursesDetailsComponent } from './components/admin/courses-details/courses-details.component';

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
  {path: 'admin/users', component: UsersComponent},
  {path: 'cart-details/:id/:email', component: CartDetailsComponent},
  {path: 'course-search', component: CourseSearchComponent},
  {path: 'admin/user-carts/:email', component: UserCartsComponent},
  {
    path: 'user-profile',
    component: UserProfileComponent,
    canActivate: [AuthGuard],
  },
  {path: 'course-display/:id', component: CourseDisplayComponent},
  {path: 'course-display/:id/:lessonId', component: CourseDisplayComponent},
  {path: 'course-creator', component: CourseCreatorComponent},
  {path: 'admin', component: AdminDashboardComponent},
  {path: 'admin/courses', component: CoursesComponent},
  {path: 'admin/course-details/:id', component: CoursesDetailsComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {
}
