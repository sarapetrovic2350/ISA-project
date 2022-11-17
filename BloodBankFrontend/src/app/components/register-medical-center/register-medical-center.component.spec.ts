import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterMedicalCenterComponent } from './register-medical-center.component';

describe('RegisterMedicalCenterComponent', () => {
  let component: RegisterMedicalCenterComponent;
  let fixture: ComponentFixture<RegisterMedicalCenterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegisterMedicalCenterComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RegisterMedicalCenterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
