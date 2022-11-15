import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateMedicalCenntarComponent } from './update-medical-cenntar.component';

describe('UpdateMedicalCenntarComponent', () => {
  let component: UpdateMedicalCenntarComponent;
  let fixture: ComponentFixture<UpdateMedicalCenntarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateMedicalCenntarComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UpdateMedicalCenntarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
