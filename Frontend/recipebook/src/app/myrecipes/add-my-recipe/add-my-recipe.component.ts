import { Component, OnInit, Inject, ElementRef, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { COMMA, ENTER } from '@angular/cdk/keycodes';
import { MatAutocompleteSelectedEvent, MatAutocomplete } from '@angular/material/autocomplete';
import { MatChipInputEvent } from '@angular/material/chips';
import { Observable } from 'rxjs';
import { map, startWith } from 'rxjs/operators';

import { RecipeService } from '@core/services/recipe.service';
import { NotificationService } from '@core/services/notification.service';
import { MyRecipesComponent } from '../myrecipes.component';

import { Recipe } from '@core/interfaces/recipe.interface';
import { Keyword } from '@core/interfaces/keyword.interface';

@Component({
  selector: 'app-add-my-recipe',
  templateUrl: './add-my-recipe.component.html',
  styleUrls: ['./add-my-recipe.component.scss']
})
export class AddMyRecipeComponent implements OnInit{

  recipeForm: FormGroup;
  separatorKeysCodes: number[] = [ENTER, COMMA];
  keywordCtrl = new FormControl();
  filteredKeywords: Observable<string[]>;
  keywords: string[] = [];
  lids: number[] = [];
  allKeywords: string[] = [];
  allKeywordObjects: Keyword[] = [];

  @ViewChild('keywordInput') keywordInput: ElementRef<HTMLInputElement>;
  @ViewChild('auto') matAutocomplete: MatAutocomplete;

  constructor(
    private formBuilder: FormBuilder,
    public dialogRef: MatDialogRef<AddMyRecipeComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Recipe,
    public rs: RecipeService,
    private ns: NotificationService
  ) {
      this.recipeForm = this.formBuilder.group({ //???? addMyRecipeForm???
      title: [null, Validators.required],
      ingredients: [null, Validators.required],
      instructions: [null, Validators.required],
      category: null,
      status: null,
      keywords: []
      });

      this.filteredKeywords = this.keywordCtrl.valueChanges.pipe(
        startWith(null), //???
        map((keyword: string | null) => keyword ? this._filter(keyword) : this.allKeywords.slice()));
   }

   ngOnInit(): void {
    this.rs.getKeyword().then((ls: Keyword[]) => {
      this.allKeywordObjects = ls;
      ls.forEach((value, key) => {
        this.allKeywords.push(value['text']);
      });
      if (this.data) {
        this.recipeForm.disable();
        this.keywordCtrl.disable();
        this.recipeForm.patchValue(this.data);
        this.data.keywords.map(l => {
          this.keywords.push(l.text);
        });
      }
    });
  }

  addMyRecipe(form: FormGroup) {
    if (form.valid) {
      form.patchValue({'keywords': this.lids})
      console.log(form.value);
      this.rs.addMyRecipe(<Recipe>form.value);
      this.recipeForm.reset();
      this.dialogRef.close();
    }
    else {
      this.ns.show('HIBA! Az adatok nem megfelelőek!');
    }
  }

  keywordAdd(event: MatChipInputEvent): void {
    const input = event.input;
    const value = event.value;
    if ((value || '').trim()) {
      this.keywords.push(value.trim());
    }
    if (input) {
      input.value = '';
    }
    this.rs.addKeyword(value).then(lid => {
      this.lids.push(lid);
    });
    this.keywordCtrl.setValue(null);
  }

  keywordSelected(event: MatAutocompleteSelectedEvent): void {
    this.keywords.push(event.option.viewValue);
    this.keywordInput.nativeElement.value = '';
    this.keywordCtrl.setValue(null);
    this.lids.push(this.allKeywordObjects.find(l => l.text === event.option.viewValue).id);
  }

  private _filter(value: string): string[] {
    const filterValue = value.toLowerCase();
    return this.allKeywords.filter(keyword => keyword.toLowerCase().indexOf(filterValue) === 0);
  }

}
