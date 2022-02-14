import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminWorkshopComponent } from './admin-workshop.component';

describe('AdminWorkshopComponent', () => {
  let component: AdminWorkshopComponent;
  let fixture: ComponentFixture<AdminWorkshopComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminWorkshopComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminWorkshopComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
