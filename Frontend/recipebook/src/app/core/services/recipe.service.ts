import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';

import { NotificationService } from '@core/services/notification.service';

import { Recipe } from '@core/interfaces/recipe.interface';
import { Keyword } from '@core/interfaces/keyword.interface';

import { baseUrl } from 'src/environments/environment';

@Injectable({
	providedIn: 'root'
})
export class RecipeService {
    recipes$ = new BehaviorSubject<Recipe[]>([]);
    recipesByCat = new BehaviorSubject<Recipe[]>([]);
    keywords: Keyword[] = [];
    rec: Recipe;
    recs: Recipe[];


    constructor(
        private http: HttpClient,
        private ns: NotificationService
    ) {}

    getRecipeById(id: number): Observable<Recipe>{
     return this.http.get<Recipe>(`${baseUrl}/recipes/${id}`);
    }

    getRecipesByCategory(category:string): void{
      this.http.get<Recipe[]>(`${baseUrl}/recipes/category/${category.toUpperCase()}`)
            .subscribe(r => {
                this.recipesByCat.next(r);
            });
    }

    getMyRecipes(): void {
        const header = new HttpHeaders().set(
            'Authorization', `Bearer ${localStorage.getItem('token')}`
        );
        this.http.get<Recipe[]>(`${baseUrl}/recipes/recipes/own`, {headers: header})
            .subscribe(r => {
                this.recipes$.next(r);
            });
    }

    getRecipes(): Observable<Recipe[]> {
      return this.http.get<Recipe[]>(`${baseUrl}/recipes`);
    }

    deleteRecipe(id: number) {
      const header = new HttpHeaders().set(
        'Authorization', `Bearer ${localStorage.getItem('token')}`
    );
      this.http.delete<Recipe>(`${baseUrl}/recipes/${id}`,{headers: header} ).subscribe(
        ni => {
          this.recipes$.next(this.recipes$.getValue().filter(item => item.id !== id));
          this.ns.show('Recept törölve!');
      },
      error => {
          this.ns.show('HIBA! Törlés sikertelen!');
          console.error(error);
      }
      );
    }

    addMyRecipe(recipe: Recipe) {
        const header = new HttpHeaders().set(
            'Authorization', `Bearer ${localStorage.getItem('token')}`
        );
        this.http.post<Recipe>(`${baseUrl}/recipes`, recipe, {headers: header})
        .subscribe(
            ni => {
                this.recipes$.next(this.recipes$.getValue().concat([ni]));
                this.ns.show('Recept hozzáadva!');
            },
            error => {
                this.ns.show('HIBA! Új recept hozzáadása sikertelen!');
                console.error(error);
            }
        );
    }

    async getKeyword(): Promise<Keyword[]> {
        const header = new HttpHeaders().set(
            'Authorization', `Bearer ${localStorage.getItem('token')}`
        );
        const keywords: Keyword[] = await this.http.get<Keyword[]>(`${baseUrl}/keywords`, {headers: header}).toPromise();
        return keywords;
    }

    async addKeyword(keyword: string) {
        const header = new HttpHeaders().set(
            'Authorization', `Bearer ${localStorage.getItem('token')}`
        );
        const id = await this.http.post<number>(`${baseUrl}/keywords`, {'text': keyword}, {headers: header}).toPromise()
        .then(
            l => {
                this.ns.show('Új kulcsszó hozzáadva!');
                return l['id'];
            })
            .catch(error => {
                this.ns.show('HIBA! Új kulcszsó hozzáadása sikertelen!');
                console.error(error);
            }
        );
        return id;
    }

}
