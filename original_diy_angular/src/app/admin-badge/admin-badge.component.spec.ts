import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminBadgeComponent } from './admin-badge.component';

describe('AdminBadgeComponent', () => {
  let component: AdminBadgeComponent;
  let fixture: ComponentFixture<AdminBadgeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminBadgeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminBadgeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
