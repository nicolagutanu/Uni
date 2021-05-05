import { Component, OnInit } from '@angular/core';
import {ServiceRecipeService} from "../service-recipe.service";
import {Recipe} from "../recipe";

@Component({
  selector: 'app-update-page',
  templateUrl: './update-page.component.html',
  styleUrls: ['./update-page.component.scss']
})
export class UpdatePageComponent implements OnInit {
  errorMessage: string="";
  constructor(private service: ServiceRecipeService) { }

  ngOnInit(): void {
  }

  UpdateRecipe(id, author, name, type, desc): void {
    this.errorMessage="";
    let newRecipe : Recipe = {id, author, name, type, desc};
    this.service.UpdateRecipe(newRecipe)
      .subscribe(response => {
        this.errorMessage=response;
      })
  }
}
