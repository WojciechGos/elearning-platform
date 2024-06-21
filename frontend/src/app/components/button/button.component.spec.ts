import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ButtonComponent } from './button.component';
import { By } from '@angular/platform-browser';
import { Type } from './button.types';
describe('ButtonComponent', () => {
  let component: ButtonComponent;
  let fixture: ComponentFixture<ButtonComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ButtonComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ButtonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should set default class if type is not provided', () => {
    component.type = Type.DEFAULT;
    fixture.detectChanges();
    const divElement = fixture.debugElement.query(By.css('div'));
    expect(divElement.classes['default-button']).toBeTrue();
  });

  it('should set the correct class based on type input', () => {
    component.type = Type.BUY;
    fixture.detectChanges();
    let divElement = fixture.debugElement.query(By.css('div'));
    expect(divElement.classes['buy-button']).toBeTrue();

    component.type = Type.LOG_IN;
    fixture.detectChanges();
    divElement = fixture.debugElement.query(By.css('div'));
    expect(divElement.classes['log-in-button']).toBeTrue();

    component.type = Type.SIGN_UP;
    fixture.detectChanges();
    divElement = fixture.debugElement.query(By.css('div'));
    expect(divElement.classes['sign-up-button']).toBeTrue();

    component.type = Type.SUBMIT;
    fixture.detectChanges();
    divElement = fixture.debugElement.query(By.css('div'));
    expect(divElement.classes['submit-button']).toBeTrue();
  });

  it('should emit an event when the button is clicked', () => {
    spyOn(component.buttonClick, 'emit');

    const divElement = fixture.debugElement.query(By.css('div'));
    divElement.triggerEventHandler('click', null);

    expect(component.buttonClick.emit).toHaveBeenCalled();
  });

  it('should display the correct text', () => {
    component.text = 'Click Me';
    fixture.detectChanges();

    const divElement = fixture.debugElement.query(By.css('div')).nativeElement;
    expect(divElement.textContent).toContain('Click Me');
  });
});