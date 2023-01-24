import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HistoryOfVisitsComponent } from './history-of-visits.component';

describe('HistoryOfVisitsComponent', () => {
  let component: HistoryOfVisitsComponent;
  let fixture: ComponentFixture<HistoryOfVisitsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HistoryOfVisitsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HistoryOfVisitsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
