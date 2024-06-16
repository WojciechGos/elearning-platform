import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Router } from '@angular/router';
import { of } from 'rxjs';
import { NavbarComponent } from './navbar.component';
import { CategoryService } from 'src/app/services/category/category.service';
import { AuthService } from 'src/app/services/auth.service';
import { Category } from 'src/app/interfaces/category.interface';

describe('NavbarComponent', () => {
  let component: NavbarComponent;
  let fixture: ComponentFixture<NavbarComponent>;
  let routerMock: any;
  let categoryServiceMock: any;
  let authServiceMock: any;

  const mockCategories: Category[] = [
    {
      id: 1,
      name: 'Category 1',
      description: '',
    },
    {
      id: 2,
      name: 'Category 2',
      description: '',
    },
  ];

  const mockUser = { firstName: 'John', lastName: 'Doe' };

  beforeEach(async () => {
    routerMock = jasmine.createSpyObj('Router', ['navigate']);
    categoryServiceMock = jasmine.createSpyObj('CategoryService', [
      'getCategories',
    ]);
    categoryServiceMock.getCategories.and.returnValue(of(mockCategories));
    authServiceMock = {
      currentUser: of(mockUser),
    };

    await TestBed.configureTestingModule({
      declarations: [NavbarComponent],
      providers: [
        { provide: Router, useValue: routerMock },
        { provide: CategoryService, useValue: categoryServiceMock },
        { provide: AuthService, useValue: authServiceMock },
      ],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NavbarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should set isLoggedIn and userInitials on init', () => {
    component.ngOnInit();
    expect(component.isLoggedIn).toBe(true);
    expect(component.userInitials).toBe('JD');
  });

  it('should call router.navigate with correct query params on search', () => {
    component.searchInput = 'test';
    component.onSearchHandler();
    expect(routerMock.navigate).toHaveBeenCalledWith(['/course-search'], {
      queryParams: { keyword: 'test' },
    });
  });

  it('should show list and fetch categories if not already fetched', () => {
    component.showList();
    expect(component.isListVisible).toBe(true);
    expect(categoryServiceMock.getCategories).toHaveBeenCalled();
    expect(component.categories.length).toBe(2);
    expect(component.categories).toEqual(mockCategories);
  });

  it('should hide list', () => {
    component.hideList();
    expect(component.isListVisible).toBe(false);
  });

  it('should toggle isMenuVisible', () => {
    component.toggleMenu();
    expect(component.isMenuVisible).toBe(true);
    component.toggleMenu();
    expect(component.isMenuVisible).toBe(false);
  });

  it('should call router.navigate to user profile on navigateToUserProfile', () => {
    component.navigateToUserProfile();
    expect(routerMock.navigate).toHaveBeenCalledWith(['/user-profile']);
  });
});
