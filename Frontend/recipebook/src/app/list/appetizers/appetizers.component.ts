import { Component, OnInit } from '@angular/core';
import { RecipeService } from '@core/services/recipe.service';
import { Recipe } from '@core/interfaces/recipe.interface';
import {MatDialog} from '@angular/material/dialog';


@Component({
  selector: 'app-appetizers',
  templateUrl: './appetizers.component.html',
  styleUrls: ['./appetizers.component.scss']
})
export class AppetizersComponent{


  constructor(
    public rs: RecipeService,
    public dialog: MatDialog
  ) { }

  ngOnInit(): void {
   this.rs.getRecipesByCategory("soup");
  }

  // openDialog(): void {
	// 	const dialogRef = this.dialog.open(DialogComponent);

  //     dialogRef.afterClosed().subscribe(result => {
  //     console.log(`Dialog result: ${result}`);
	// 	})
  // }
}

