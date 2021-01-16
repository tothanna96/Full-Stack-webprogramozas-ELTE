import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FlexLayoutModule } from '@angular/flex-layout';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatCardModule } from '@angular/material/card';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { MatChipsModule } from '@angular/material/chips';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatDialogModule } from '@angular/material/dialog';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { MatRippleModule } from '@angular/material/core';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import {MatAutocompleteModule} from '@angular/material/autocomplete';
import {MatTabsModule} from '@angular/material/tabs';
import {MatRadioModule} from '@angular/material/radio';



import { AppRoutingModule } from './app-routing.module';
import { AuthGuard } from '@core/guards/auth.guard';
//import { AnonymGuard } from '@core/guards/anonym.guard';

import { NameFormatDirective } from '@core/directives/nameformat.directive';

import { AppComponent } from './app.component';
import { MenuComponent } from './menu/menu.component';
import { ProfileComponent } from './profile/profile.component';
import { RecipesComponent } from './recipes/recipes.component';
	import { RecipeComponent } from './recipes/recipe/recipe.component';
import { MyRecipesComponent } from './myrecipes/myrecipes.component';
	import { AddMyRecipeComponent } from './myrecipes/add-my-recipe/add-my-recipe.component';
	import { MyfoldersComponent } from './myrecipes/myfolders/myfolders.component';
import { AuthComponent } from './auth/auth.component';
	import { SigninComponent } from './auth/signin/signin.component';
	import { SignupComponent } from './auth/signup/signup.component';
import { ListComponent } from './list/list.component';
	import { AppetizersComponent } from './list/appetizers/appetizers.component';
	import { MainfoodComponent } from './list/mainfood/mainfood.component';
	import { DessertsComponent } from './list/desserts/desserts.component';
import { PagenotfoundComponent } from './pagenotfound/pagenotfound.component';
import {MatGridListModule} from '@angular/material/grid-list';

@NgModule({
	declarations: [
		NameFormatDirective,
		AppComponent,
		MenuComponent,
		ProfileComponent,
		RecipesComponent,
		SigninComponent,
		SignupComponent,
		MyRecipesComponent,
		AddMyRecipeComponent,
		RecipeComponent,
		AuthComponent,
		ListComponent,
		AppetizersComponent,
		MainfoodComponent,
		DessertsComponent,
		MyfoldersComponent,
		PagenotfoundComponent
	],
	imports: [
		BrowserModule,
		BrowserAnimationsModule,
		FormsModule,
		ReactiveFormsModule,
		HttpClientModule,
		FlexLayoutModule,
		MatToolbarModule,
		MatSidenavModule,
		MatCardModule,
		MatInputModule,
		MatSelectModule,
		MatButtonModule,
		MatChipsModule,
		MatTooltipModule,
		MatDialogModule,
		MatIconModule,
		MatListModule,
		MatRippleModule,
		MatExpansionModule,
		MatSnackBarModule,
		AppRoutingModule,
		MatAutocompleteModule,
		MatTabsModule,
    MatRadioModule,
    MatGridListModule
	],
	providers: [
		AuthGuard,
		//AnonymGuard
	],
	bootstrap: [AppComponent]
})
export class AppModule { }
