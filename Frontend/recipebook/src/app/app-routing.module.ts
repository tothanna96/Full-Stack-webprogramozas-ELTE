import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AuthComponent } from './auth/auth.component';
import { RecipesComponent } from './recipes/recipes.component';
import { MyRecipesComponent } from './myrecipes/myrecipes.component';
import { ListComponent } from './list/list.component';
import { RecipeComponent } from './recipes/recipe/recipe.component';
import { AppetizersComponent } from './list/appetizers/appetizers.component';
import { MainfoodComponent } from './list/mainfood/mainfood.component';
import { DessertsComponent } from './list/desserts/desserts.component';
import { MyfoldersComponent } from './myrecipes/myfolders/myfolders.component';
import { ProfileComponent } from './profile/profile.component';

import { PagenotfoundComponent } from './pagenotfound/pagenotfound.component';
import { AuthGuard } from '@core/guards/auth.guard';

const routes: Routes = [
  {path: 'profile', component: ProfileComponent},
	{ path: '', component: AuthComponent },
	{ path: 'recipes/open', component: RecipesComponent },
	{ path: 'recipes/closed', component: RecipesComponent },
	{ path: 'myrecipes/open', component: MyRecipesComponent, canActivate: [AuthGuard] },
	{ path: 'myrecipes/closed', component: MyRecipesComponent, canActivate: [AuthGuard]},
	{ path: 'list/open', component: ListComponent },
	{ path: 'list/close', component: ListComponent },
	{ path: 'recipe/:id', component: RecipeComponent },
	{ path: 'recipe/close', component: RecipeComponent },
	{ path: 'appetizers/open', component: AppetizersComponent },
	{ path: 'appetizers/close', component: AppetizersComponent },
	{ path: 'mainfood/open', component: MainfoodComponent },
	{ path: 'mainfood/close', component: MainfoodComponent },
	{ path: 'desserts/open', component: DessertsComponent },
	{ path: 'desserts/close', component: DessertsComponent },
	{ path: 'myfolders/open', component: MyfoldersComponent },
	{ path: 'myfolders/close', component: MyfoldersComponent },
	{ path: '404', component: PagenotfoundComponent },
		{ path: '**', redirectTo: '404', pathMatch: 'full' }
];

@NgModule({
	imports: [RouterModule.forRoot(routes)],
	exports: [RouterModule]
})
export class AppRoutingModule { }
