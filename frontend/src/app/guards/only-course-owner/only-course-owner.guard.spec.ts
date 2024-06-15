import { TestBed } from '@angular/core/testing';
import { CanActivateFn } from '@angular/router';

import { onlyCourseOwnerGuard } from './only-course-owner.guard';

describe('onlyCourseOwnerGuard', () => {
  const executeGuard: CanActivateFn = (...guardParameters) => 
      TestBed.runInInjectionContext(() => onlyCourseOwnerGuard(...guardParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeGuard).toBeTruthy();
  });
});
