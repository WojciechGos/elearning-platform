import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { Router } from '@angular/router';
import { CartComponent } from './cart.component';
import { CartService } from 'src/app/services/cart/cart.service';
import { PaymentService } from 'src/app/services/payment/payment.service';
import { AuthService } from 'src/app/services/auth.service';
import { CartItem } from 'src/app/interfaces/cartItem.interface';
import { Course } from 'src/app/interfaces/course.interface';

describe('CartComponent', () => {
  let component: CartComponent;
  let fixture: ComponentFixture<CartComponent>;
  let cartServiceMock: any;
  let paymentServiceMock: any;
  let authServiceMock: any;
  let routerMock: any;

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
      courseState: 'active',
      targetAudience: 'beginners'
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
      courseState: 'active',
      targetAudience: 'intermediate'
    }
  ];

  const mockCartItems: CartItem[] = [
    { id: 1, course: mockCourses[0] },
    { id: 2, course: mockCourses[1] }
  ];

  beforeEach(async () => {
    cartServiceMock = jasmine.createSpyObj('CartService', ['getCart']);
    cartServiceMock.getCart.and.returnValue(of({
      items: mockCartItems,
      id: 1
    }));

    paymentServiceMock = jasmine.createSpyObj('PaymentService', ['pay']);

    authServiceMock = {
      currentUser: of({ id: 1, name: 'Test User' })
    };

    routerMock = jasmine.createSpyObj('Router', ['navigate']);

    await TestBed.configureTestingModule({
      declarations: [CartComponent],
      providers: [
        { provide: CartService, useValue: cartServiceMock },
        { provide: PaymentService, useValue: paymentServiceMock },
        { provide: AuthService, useValue: authServiceMock },
        { provide: Router, useValue: routerMock }
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should load cart items and calculate total price on init', () => {
    expect(component.cartItems.length).toBe(2);
    expect(component.totalPrice).toBe(300);
    expect(component.cartId).toBe(1);
  });

  it('should subscribe to authService and set isLoggedIn', () => {
    expect(component.isLoggedIn).toBe(true);
  });

  it('should calculate total price correctly', () => {
    component.cartItems = [
      { id: 1, course: mockCourses[0] },
      { id: 2, course: mockCourses[1] }
    ];
    component.calculateTotalPrice();
    expect(component.totalPrice).toBe(300);
  });

  it('should call payment service on pay', () => {
    component.totalPrice = 300;
    component.cartId = 1;
    component.cartItems = mockCartItems;

    component.pay();

    expect(paymentServiceMock.pay).toHaveBeenCalledWith({
      name: 'Course 1, Course 2',
      currency: 'usd',
      amount: 30000,
      quantity: '1',
      cancelUrl: 'http://localhost:4200/cancel',
      successUrl: 'http://localhost:4200/success?cartId=1'
    });
  });

  it('should navigate to login on goToLogin', () => {
    component.goToLogin();
    expect(routerMock.navigate).toHaveBeenCalledWith(['/login']);
  });
});
