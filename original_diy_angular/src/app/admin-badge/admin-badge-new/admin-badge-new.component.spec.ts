import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminBadgeNewComponent } from './admin-badge-new.component';

describe('AdminBadgeNewComponent', () => {
  let component: AdminBadgeNewComponent;
  let fixture: ComponentFixture<AdminBadgeNewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminBadgeNewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminBadgeNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
