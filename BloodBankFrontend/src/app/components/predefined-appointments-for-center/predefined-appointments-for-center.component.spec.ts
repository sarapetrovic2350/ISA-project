import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PredefinedAppointmentsForCenterComponent } from './predefined-appointments-for-center.component';

describe('PredefinedAppointmentsForCenterComponent', () => {
  let component: PredefinedAppointmentsForCenterComponent;
  let fixture: ComponentFixture<PredefinedAppointmentsForCenterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PredefinedAppointmentsForCenterComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PredefinedAppointmentsForCenterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
