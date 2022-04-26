import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WorkshopAllComponent } from './workshop-all.component';

describe('WorkshopAllComponent', () => {
  let component: WorkshopAllComponent;
  let fixture: ComponentFixture<WorkshopAllComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WorkshopAllComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WorkshopAllComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
