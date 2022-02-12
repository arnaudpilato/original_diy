import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminContactNewComponent } from './admin-contact-new.component';

describe('AdminContactNewComponent', () => {
  let component: AdminContactNewComponent;
  let fixture: ComponentFixture<AdminContactNewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminContactNewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminContactNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
