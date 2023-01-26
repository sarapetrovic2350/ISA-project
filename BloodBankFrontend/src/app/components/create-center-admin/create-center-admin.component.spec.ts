import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateCenterAdminComponent } from './create-center-admin.component';

describe('CreateCenterAdminComponent', () => {
  let component: CreateCenterAdminComponent;
  let fixture: ComponentFixture<CreateCenterAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateCenterAdminComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreateCenterAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
