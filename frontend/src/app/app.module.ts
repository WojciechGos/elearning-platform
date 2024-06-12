import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { MatExpansionModule } from '@angular/material/expansion';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSliderModule } from '@angular/material/slider';
import { MatInputModule } from '@angular/material/input';
import { FormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/authentication/login/login.component';
import { RegisterComponent } from './components/authentication/register/register.component';
import { AppRoutingModule } from './app-routing.module';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AuthInterceptor } from './services/interceptors/auth.interceptor';
import { NavbarComponent } from './components/navbar/navbar.component';
import { CourseDetailsComponent } from './components/course-details/course-details.component';
import { MainPageComponent } from './components/main-page/main-page.component';
import { CoursesListComponent } from './components/courses-list/courses-list.component';
import { CoursesListItemComponent } from './components/courses-list-item/courses-list-item.component';
import { ButtonComponent } from './components/button/button.component';
import { RatingComponent } from './components/rating/rating.component';
import { PriceComponent } from './components/price/price.component';
import { MatListModule } from '@angular/material/list';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { CancelComponent } from './components/payment/cancel/cancel.component';
import { SuccessComponent } from './components/payment/success/success.component';
import { CartComponent } from './components/cart/cart.component';
import { CartItemComponent } from './components/cart-item/cart-item.component';
import { UsersComponent } from './components/admin/users/users.component';
import { MatSortModule } from '@angular/material/sort';
import { MatTableModule } from '@angular/material/table';
import { MatFormFieldModule } from '@angular/material/form-field';
import { CartDetailsComponent } from './components/admin/cart-details/cart-details.component';
import { CourseSearchComponent } from './components/course-search/course-search.component';
import { CartItemsListComponent } from './components/cart-items-list/cart-items-list.component';
import { UserCartsComponent } from './components/admin/user-carts/user-carts.component';
import { LessonListComponent } from './components/lesson-list/lesson-list.component';
import { CourseSearchItemComponent } from './components/course-search-item/course-search-item.component';
import { AuthService } from './services/auth.service';
import { AuthGuard } from './guards/auth.guard';
import { UserProfileComponent } from './components/user-profile/user-profile.component';
import { CourseDisplayComponent } from './components/course-display/course-display.component';
import { LessonDisplayComponent } from './components/lesson-display/lesson-display.component';
import { CourseCreatorComponent } from './components/course-creator/course-creator.component';
import { MatTabsModule } from '@angular/material/tabs';
import { CourseCreatorCourseInfoComponent } from './components/course-creator-course-info/course-creator-course-info.component';
import { CourseCreatorLessonComponent } from './components/course-creator-lesson/course-creator-lesson.component';
import { MatButtonToggleModule } from '@angular/material/button-toggle';
import { MatStepperModule } from '@angular/material/stepper';
import { MatRadioModule } from '@angular/material/radio';
import { StoreModule } from '@ngrx/store';
import { EffectsModule } from '@ngrx/effects';
import { StoreDevtoolsModule } from '@ngrx/store-devtools';
import { environment } from '../environments/environment';
import { CourseModule } from './store/course/course.module';
import { CourseCreatorLessonItemComponent } from './components/course-creator-lesson-item/course-creator-lesson-item.component';
import { CourseCreatorPublishComponent } from './components/course-creator-publish/course-creator-publish.component';
import { MatIconModule } from '@angular/material/icon'
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatSelectModule } from '@angular/material/select';
import { UserProfileCartListComponent } from './components/user-profile-cart-list/user-profile-cart-list.component';
import { UserProfileCoursesListComponent } from './components/user-profile-courses-list/user-profile-courses-list.component';
import { AdminDashboardComponent } from './components/admin/admin-dashboard/admin-dashboard.component';
import { CoursesComponent } from './components/admin/courses/courses.component';
import { CoursesDetailsComponent } from './components/admin/courses-details/courses-details.component';
import { FileUploadButtonComponent } from './components/file-upload-button/file-upload-button.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    NavbarComponent,
    CourseDetailsComponent,
    ButtonComponent,
    RatingComponent,
    PriceComponent,
    MainPageComponent,
    CoursesListComponent,
    CoursesListItemComponent,
    CancelComponent,
    SuccessComponent,
    CartComponent,
    CartItemComponent,
    CourseSearchComponent,
    UsersComponent,
    CartDetailsComponent,
    CartItemsListComponent,
    UserCartsComponent,
    LessonListComponent,
    CourseSearchItemComponent,
    CourseCreatorComponent,
    CourseCreatorCourseInfoComponent,
    CourseCreatorLessonComponent,
    CourseCreatorLessonItemComponent,
    CourseCreatorPublishComponent,
    CourseDisplayComponent,
    LessonDisplayComponent,
    UserProfileComponent,
    UserProfileCartListComponent,
    UserProfileCoursesListComponent,
    AdminDashboardComponent,
    CoursesComponent,
    CoursesDetailsComponent,
    FileUploadButtonComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatListModule,
    MatCardModule,
    MatButtonModule,
    BrowserAnimationsModule,
    MatFormFieldModule,
    MatInputModule,
    MatTableModule,
    MatSortModule,
    MatPaginatorModule,
    FormsModule,
    MatExpansionModule,
    MatSliderModule,
    FormsModule,
    MatTabsModule,
    MatStepperModule,
    MatButtonToggleModule,
    MatRadioModule,
    StoreModule.forRoot({}, {}),
    EffectsModule.forRoot([]),
    StoreDevtoolsModule.instrument({ maxAge: 25, logOnly: environment.production }),
    CourseModule,
    MatIconModule,
    MatProgressSpinnerModule,
    MatSelectModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
    AuthService,
    AuthGuard,
  ], bootstrap: [AppComponent],
})
export class AppModule { }
