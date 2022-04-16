import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminCommentaryComponent } from './admin-commentary.component';

describe('AdminCommentaryComponent', () => {
  let component: AdminCommentaryComponent;
  let fixture: ComponentFixture<AdminCommentaryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminCommentaryComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminCommentaryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
