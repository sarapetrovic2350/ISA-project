import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisteredUserAppointmentComponent } from './registered-user-appointment.component';

describe('RegisteredUserAppointmentComponent', () => {
  let component: RegisteredUserAppointmentComponent;
  let fixture: ComponentFixture<RegisteredUserAppointmentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegisteredUserAppointmentComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RegisteredUserAppointmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
