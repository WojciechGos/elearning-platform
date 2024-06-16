import { TestBed } from '@angular/core/testing';

import { PaymentService } from './payment.service';
import { HttpClientModule } from '@angular/common/http';

describe('PaymentService', () => {
  let service: PaymentService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpClientModule,
      ]
    });
    service = TestBed.inject(PaymentService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
