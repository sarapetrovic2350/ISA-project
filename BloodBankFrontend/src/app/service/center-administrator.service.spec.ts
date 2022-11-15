import { TestBed } from '@angular/core/testing';

import { CenterAdministratorService } from './center-administrator.service';

describe('CenterAdministratorService', () => {
  let service: CenterAdministratorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CenterAdministratorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
