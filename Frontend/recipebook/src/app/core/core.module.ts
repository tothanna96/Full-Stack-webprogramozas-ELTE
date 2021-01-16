import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MatSnackBarModule } from '@angular/material/snack-bar';

import { AuthService } from './services/auth.service';
import { RecipeService } from '@core/services/recipe.service';
import { NotificationService } from '@core/services/notification.service';

@NgModule({
	declarations: [],
	imports: [
		CommonModule,
		MatSnackBarModule
	],
	providers: [
		AuthService,
		RecipeService,
		NotificationService
	]
})
export class CoreModule { }
