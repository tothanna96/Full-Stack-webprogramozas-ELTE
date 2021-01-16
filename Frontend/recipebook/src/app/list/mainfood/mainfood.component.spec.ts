import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MainfoodComponent } from './mainfood.component';

describe('MainfoodComponent', () => {
  let component: MainfoodComponent;
  let fixture: ComponentFixture<MainfoodComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MainfoodComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MainfoodComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
