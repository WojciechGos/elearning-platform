import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/authentication/login/login.component';
import { RegisterComponent } from './components/authentication/register/register.component';
import { CourseDetailsComponent } from './components/course-details/course-details.component';
import { MainPageComponent } from './components/main-page/main-page.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'course-details/:id', component: CourseDetailsComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'main-page', component: MainPageComponent},
  { path: '', redirectTo: '/main-page', pathMatch: 'full' }, 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
