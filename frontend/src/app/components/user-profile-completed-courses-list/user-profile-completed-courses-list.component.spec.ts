import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { CourseService } from 'src/app/services/course/course.service';
import { Course } from 'src/app/interfaces/course.interface';
import { UserProfileCompletedCoursesListComponent } from './user-profile-completed-courses-list.component';
import { HttpClientModule } from '@angular/common/http';

describe('UserProfileCompletedCoursesListComponent', () => {
  let component: UserProfileCompletedCoursesListComponent;
  let fixture: ComponentFixture<UserProfileCompletedCoursesListComponent>;
  let courseServiceMock: any;

  const mockCourses: Course[] = [
    {
      id: 1,
      title: 'Course 1',
      description: 'Description 1',
      price: 100,
      discountPrice: 90,
      categories: ['Category 1'],
      language: 'English',
      totalDuration: 120,
      rating: 4.5,
      imageUrl: 'http://example.com/course1.jpg',
      lessons: [],
      enrollmentCount: 100,
      courseState: 'completed',
      targetAudience: 'beginners',
    },
    {
      id: 2,
      title: 'Course 2',
      description: 'Description 2',
      price: 200,
      discountPrice: 180,
      categories: ['Category 2'],
      language: 'Spanish',
      totalDuration: 150,
      rating: 4.7,
      imageUrl: 'http://example.com/course2.jpg',
      lessons: [],
      enrollmentCount: 200,
      courseState: 'completed',
      targetAudience: 'intermediate',
    },
  ];

  beforeEach(async () => {
    courseServiceMock = jasmine.createSpyObj('CourseService', [
      'getUsersCompletedCourses',
    ]);
    courseServiceMock.getUsersCompletedCourses.and.returnValue(of(mockCourses));

    await TestBed.configureTestingModule({
      declarations: [UserProfileCompletedCoursesListComponent],
      imports: [HttpClientModule],
      providers: [{ provide: CourseService, useValue: courseServiceMock }],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UserProfileCompletedCoursesListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should load completed courses on init', () => {
    component.ngOnInit();
    expect(component.courses.length).toBe(2);
    expect(component.courses).toEqual(mockCourses);
  });
});
