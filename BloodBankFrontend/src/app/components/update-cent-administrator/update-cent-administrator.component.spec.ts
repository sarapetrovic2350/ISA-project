import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateCentAdministratorComponent } from './update-cent-administrator.component';

describe('UpdateCentAdministratorComponent', () => {
  let component: UpdateCentAdministratorComponent;
  let fixture: ComponentFixture<UpdateCentAdministratorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateCentAdministratorComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UpdateCentAdministratorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
