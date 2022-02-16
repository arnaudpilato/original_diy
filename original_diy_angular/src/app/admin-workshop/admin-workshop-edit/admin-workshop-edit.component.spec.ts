import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminWorkshopEditComponent } from './admin-workshop-edit.component';

describe('AdminWorkshopEditComponent', () => {
  let component: AdminWorkshopEditComponent;
  let fixture: ComponentFixture<AdminWorkshopEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminWorkshopEditComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminWorkshopEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
