import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddMyRecipeComponent } from './add-my-recipe.component';

describe('AddMyRecipeComponent', () => {
  let component: AddMyRecipeComponent;
  let fixture: ComponentFixture<AddMyRecipeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddMyRecipeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddMyRecipeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
