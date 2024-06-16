import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Router } from '@angular/router';
import { of, throwError } from 'rxjs';
import { Store } from '@ngrx/store';
import { AuthService } from 'src/app/services/auth.service';
import { CourseService } from 'src/app/services/course/course.service';
import { PermissionService } from 'src/app/services/permission/permission.service';
import { UserProfileComponent } from './user-profile.component';
import { setCourse } from 'src/app/store/course/course.actions';
import { Course } from 'src/app/interfaces/course.interface';

describe('UserProfileComponent', () => {
  let component: UserProfileComponent;
  let fixture: ComponentFixture<UserProfileComponent>;
  let authServiceMock: any;
  let courseServiceMock: any;
  let storeMock: any;
  let routerMock: any;
  let permissionServiceMock: any;

  const mockUser = { id: 1, firstName: 'John', lastName: 'Doe' };
  const mockCourse: Course = {
    id: 1,
    title: 'New Course',
    description: 'Course Description',
    price: 100,
    discountPrice: 90,
    categories: ['Category 1'],
    language: 'English',
    totalDuration: 120,
    rating: 4.5,
    imageUrl: 'http://example.com/course.jpg',
    lessons: [],
    enrollmentCount: 0,
    courseState: 'draft',
    targetAudience: 'beginners',
  };

  beforeEach(async () => {
    authServiceMock = jasmine.createSpyObj('AuthService', [
      'currentUser',
      'logout',
    ]);
    authServiceMock.currentUser = of(mockUser);
    authServiceMock.logout.and.returnValue(of({}));

    courseServiceMock = jasmine.createSpyObj('CourseService', [
      'createEmptyCourse',
    ]);
    courseServiceMock.createEmptyCourse.and.returnValue(of(mockCourse));

    storeMock = jasmine.createSpyObj('Store', ['dispatch']);

    routerMock = jasmine.createSpyObj('Router', ['navigateByUrl']);

    permissionServiceMock = jasmine.createSpyObj('PermissionService', [
      'checkUserPermission',
    ]);
    permissionServiceMock.checkUserPermission.and.returnValue(of(true));

    await TestBed.configureTestingModule({
      declarations: [UserProfileComponent],
      providers: [
        { provide: AuthService, useValue: authServiceMock },
        { provide: CourseService, useValue: courseServiceMock },
        { provide: Store, useValue: storeMock },
        { provide: Router, useValue: routerMock },
        { provide: PermissionService, useValue: permissionServiceMock },
      ],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UserProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should set user and isAdmin on init', () => {
    component.ngOnInit();
    expect(component.user).toEqual(mockUser);
    expect(component.isAdmin).toBe(true);
  });

  it('should call logout on AuthService and log message on success', () => {
    spyOn(console, 'log');
    component.logout();
    expect(authServiceMock.logout).toHaveBeenCalled();
    expect(console.log).toHaveBeenCalledWith('Logged out');
  });

  it('should call createEmptyCourse on CourseService and navigate on createCourse', () => {
    component.createCourse();
    expect(courseServiceMock.createEmptyCourse).toHaveBeenCalled();
    expect(storeMock.dispatch).toHaveBeenCalledWith(
      setCourse({ course: mockCourse })
    );
    expect(routerMock.navigateByUrl).toHaveBeenCalledWith('/course-creator');
  });

  it('should handle logout failure', () => {
    authServiceMock.logout.and.returnValue(
      throwError(() => new Error('Logout failed'))
    );
    spyOn(console, 'error');
    component.logout();
    expect(console.error).toHaveBeenCalledWith(
      'Logout failed',
      jasmine.any(Error)
    );
  });
});
