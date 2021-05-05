import { Component, OnInit } from '@angular/core';
import {ServiceRecipeService} from "../service-recipe.service";
import {Recipe} from "../recipe";

@Component({
  selector: 'app-add-page',
  templateUrl: './add-page.component.html',
  styleUrls: ['./add-page.component.scss']
})
export class AddPageComponent implements OnInit {
  errorMessage: string="";
  constructor(private service: ServiceRecipeService) { }

  ngOnInit(): void {
  }

  AddRecipe(id, author, name, type, desc): void {
    this.errorMessage="";
    let newRecipe : Recipe = {id, author, name, type, desc};
    this.service.AddRecipe(newRecipe)
      .subscribe(response => {
        this.errorMessage=response;
      })
  }
}
