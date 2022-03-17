import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminBackgroundEditComponent } from './admin-background-edit.component';

describe('AdminBackgroundEditComponent', () => {
  let component: AdminBackgroundEditComponent;
  let fixture: ComponentFixture<AdminBackgroundEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminBackgroundEditComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminBackgroundEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
