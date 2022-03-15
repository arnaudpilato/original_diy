import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WorskhopProfilComponent } from './worskhop-profil.component';

describe('WorskhopProfilComponent', () => {
  let component: WorskhopProfilComponent;
  let fixture: ComponentFixture<WorskhopProfilComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WorskhopProfilComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WorskhopProfilComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
