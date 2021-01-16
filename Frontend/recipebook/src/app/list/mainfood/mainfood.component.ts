import { Component, OnInit } from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import { RecipeService } from '@core/services/recipe.service';


@Component({
  selector: 'app-mainfood',
  templateUrl: './mainfood.component.html',
  styleUrls: ['./mainfood.component.scss']
})
export class MainfoodComponent implements OnInit {

  constructor(public rs: RecipeService, public dialog: MatDialog) { }

  ngOnInit():void{
    this.rs.getRecipesByCategory("maincourse");
  }


  // openDialog(): void {
	// 	const dialogRef = this.dialog.open(DialogComponent);

  //     dialogRef.afterClosed().subscribe(result => {
  //     console.log(`Dialog result: ${result}`);
	// 	})
  // }

}
