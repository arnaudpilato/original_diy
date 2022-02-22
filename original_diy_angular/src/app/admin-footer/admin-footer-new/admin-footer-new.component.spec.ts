import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminFooterNewComponent } from './admin-footer-new.component';

describe('AdminFooterNewComponent', () => {
  let component: AdminFooterNewComponent;
  let fixture: ComponentFixture<AdminFooterNewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminFooterNewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminFooterNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
