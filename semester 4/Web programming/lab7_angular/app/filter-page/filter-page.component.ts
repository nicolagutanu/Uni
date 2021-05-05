import { Component, OnInit } from '@angular/core';
import { Recipe } from "../recipe";
import { ServiceRecipeService } from "../service-recipe.service";

@Component({
  selector: 'app-filter-page',
  templateUrl: './filter-page.component.html',
  styleUrls: ['./filter-page.component.scss']
})

export class FilterPageComponent implements OnInit {

  recipes: Recipe[] = [];

  constructor(private recipeService: ServiceRecipeService) { }

  ngOnInit(): void {
    this.getRecipes();
  }

  getRecipes(): void {
    this.recipeService.getRecipes()
      .subscribe(recipes => this.recipes = recipes);
  }

  getRecipeByType(type): void {
    this.recipeService.getRecipeByType(type)
      .subscribe(recipes => this.recipes = recipes);
  }

}
