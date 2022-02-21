import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminFooterEditComponent } from './admin-footer-edit.component';

describe('AdminFooterEditComponent', () => {
  let component: AdminFooterEditComponent;
  let fixture: ComponentFixture<AdminFooterEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminFooterEditComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminFooterEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
