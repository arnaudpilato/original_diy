import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminBackgroundComponent } from './admin-background.component';

describe('AdminBackgroundComponent', () => {
  let component: AdminBackgroundComponent;
  let fixture: ComponentFixture<AdminBackgroundComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminBackgroundComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminBackgroundComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
