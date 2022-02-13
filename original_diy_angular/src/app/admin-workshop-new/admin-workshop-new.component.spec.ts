import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminWorkshopNewComponent } from './admin-workshop-new.component';

describe('AdminWorkshopNewComponent', () => {
  let component: AdminWorkshopNewComponent;
  let fixture: ComponentFixture<AdminWorkshopNewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminWorkshopNewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminWorkshopNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
