import { TestBed } from '@angular/core/testing';

import { SecuredGuard } from './secured.guard';

describe('SecuredGuard', () => {
  let guard: SecuredGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(SecuredGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
