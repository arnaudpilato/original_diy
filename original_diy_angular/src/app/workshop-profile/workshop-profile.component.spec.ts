import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WorkshopProfileComponent } from './workshop-profile.component';

describe('WorkshopProfileComponent', () => {
  let component: WorkshopProfileComponent;
  let fixture: ComponentFixture<WorkshopProfileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WorkshopProfileComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WorkshopProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
