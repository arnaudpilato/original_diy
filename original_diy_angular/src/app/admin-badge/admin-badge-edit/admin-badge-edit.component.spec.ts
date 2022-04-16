import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminBadgeEditComponent } from './admin-badge-edit.component';

describe('AdminBadgeEditComponent', () => {
  let component: AdminBadgeEditComponent;
  let fixture: ComponentFixture<AdminBadgeEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminBadgeEditComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminBadgeEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
