import { Component, OnInit } from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import { RecipeService } from '@core/services/recipe.service';

@Component({
  selector: 'app-desserts',
  templateUrl: './desserts.component.html',
  styleUrls: ['./desserts.component.scss']
})
export class DessertsComponent implements OnInit {

  constructor(
    public rs: RecipeService,
    public dialog: MatDialog) { }

  ngOnInit():void{
    this.rs.getRecipesByCategory("dessert");
  }

  // openDialog(): void {
	// 	const dialogRef = this.dialog.open(DialogComponent);

  //     dialogRef.afterClosed().subscribe(result => {
  //     console.log(`Dialog result: ${result}`);
	// 	})
  // }

}
