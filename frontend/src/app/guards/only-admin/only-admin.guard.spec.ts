import { TestBed } from '@angular/core/testing';
import { CanActivateFn } from '@angular/router';

import { onlyAdminGuard } from './only-admin.guard';

describe('onlyAdminGuard', () => {
  const executeGuard: CanActivateFn = (...guardParameters) => 
      TestBed.runInInjectionContext(() => onlyAdminGuard(...guardParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeGuard).toBeTruthy();
  });
});
