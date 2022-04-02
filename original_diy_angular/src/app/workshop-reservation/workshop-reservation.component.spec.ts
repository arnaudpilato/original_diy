import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WorkshopReservationComponent } from './workshop-reservation.component';

describe('WorkshopReservationComponent', () => {
  let component: WorkshopReservationComponent;
  let fixture: ComponentFixture<WorkshopReservationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WorkshopReservationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WorkshopReservationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
