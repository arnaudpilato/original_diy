import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserWorkshopIndexComponent } from './user-workshop-index.component';

describe('UserWorkshopIndexComponent', () => {
  let component: UserWorkshopIndexComponent;
  let fixture: ComponentFixture<UserWorkshopIndexComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserWorkshopIndexComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UserWorkshopIndexComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
