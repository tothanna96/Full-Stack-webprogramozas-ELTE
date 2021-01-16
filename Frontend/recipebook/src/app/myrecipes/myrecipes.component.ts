import { Component, EventEmitter, Input, Output } from '@angular/core';

import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

import { RecipeService } from '@core/services/recipe.service';
import { AddMyRecipeComponent } from './add-my-recipe/add-my-recipe.component';

import { Recipe } from '@core/interfaces/recipe.interface';


@Component({
  selector: 'app-myrecipes',
  templateUrl: './myrecipes.component.html',
  styleUrls: ['./myrecipes.component.scss']
})

export class MyRecipesComponent{

  @Input() recipe: Recipe = null;
  @Output() deleteRecipe: EventEmitter<Recipe> = new EventEmitter();

  constructor(
    public dialog: MatDialog,
		public rs: RecipeService
  ) { }

  ngOnInit():void{
    this.rs.getMyRecipes();
  }

  openAddMyRecipeDialog(): void {
		const dialogRef = this.dialog.open(AddMyRecipeComponent, {
      width: '1000px'
    })

  }

  onDelete(recipe: Recipe) {
    this.deleteRecipe.emit(recipe);
  }

}
