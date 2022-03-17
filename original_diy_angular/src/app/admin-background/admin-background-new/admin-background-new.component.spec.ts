import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminBackgroundNewComponent } from './admin-background-new.component';

describe('AdminBackgroundNewComponent', () => {
  let component: AdminBackgroundNewComponent;
  let fixture: ComponentFixture<AdminBackgroundNewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminBackgroundNewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminBackgroundNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
