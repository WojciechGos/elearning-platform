import { ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { PriceComponent } from './price.component';

describe('PriceComponent', () => {
  let component: PriceComponent;
  let fixture: ComponentFixture<PriceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PriceComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PriceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should display price if price input is provided', () => {
    component.price = 199.99;
    fixture.detectChanges();

    const priceElement = fixture.debugElement.query(By.css('.price'));
    expect(priceElement).toBeTruthy();
    expect(priceElement.nativeElement.textContent).toContain('199.99$');
  });

  it('should not display price if price input is not provided', () => {
    component = fixture.componentInstance;
    fixture.detectChanges();

    const priceElement = fixture.debugElement.query(By.css('.price'));
    expect(priceElement).toBeFalsy();
  });

  it('should display discount price if discountPrice input is provided', () => {
    component.discountPrice = 149.99;
    fixture.detectChanges();

    const discountPriceElement = fixture.debugElement.query(By.css('.discount'));
    expect(discountPriceElement).toBeTruthy();
    expect(discountPriceElement.nativeElement.textContent).toContain('149.99$');
  });

  it('should not display discount price if discountPrice input is not provided', () => {
    component = fixture.componentInstance;
    fixture.detectChanges();

    const discountPriceElement = fixture.debugElement.query(By.css('.discount'));
    expect(discountPriceElement).toBeFalsy();
  });
});
