import { Component, OnInit, Input, Output, EventEmitter} from '@angular/core';
import { Recipe } from '@core/interfaces/recipe.interface';
import { ActivatedRoute } from '@angular/router';
import { RecipeService } from '@core/services/recipe.service';
import { stringify } from '@angular/compiler/src/util';

@Component({
  selector: 'app-recipe',
  templateUrl: './recipe.component.html',
  styleUrls: ['./recipe.component.scss']
})
export class RecipeComponent implements OnInit {

  @Input() recipe : Recipe;
  recipeId : number;
  ingredientList : string[]=[];

  constructor(public rs : RecipeService, private actRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.recipeId = this.actRoute.snapshot.params.id;
    this.rs.getRecipeById(this.recipeId).subscribe(
      r => this.recipe= r,
    );

  }

  ingredientsToList(str : string): string[]{
    return str?.split(', ');
  }



}

