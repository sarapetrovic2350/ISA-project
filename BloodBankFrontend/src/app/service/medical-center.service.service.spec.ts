import { TestBed } from '@angular/core/testing';

import { MedicalCenterServiceService } from './medical-center.service.service';

describe('MedicalCenterServiceService', () => {
  let service: MedicalCenterServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MedicalCenterServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
