import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowBloodComponent } from './show-blood.component';

describe('ShowBloodComponent', () => {
  let component: ShowBloodComponent;
  let fixture: ComponentFixture<ShowBloodComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShowBloodComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShowBloodComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
