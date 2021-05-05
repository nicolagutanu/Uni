import { Component, OnInit } from '@angular/core';
import {ServiceRecipeService} from "../service-recipe.service";

@Component({
  selector: 'app-delete-page',
  templateUrl: './delete-page.component.html',
  styleUrls: ['./delete-page.component.scss']
})
export class DeletePageComponent implements OnInit {
  errorMessage: string="";
  constructor(private service: ServiceRecipeService) { }

  ngOnInit(): void {
  }

  DeleteRecipe(id): void {
    this.errorMessage="";
    this.service.DeleteRecipe(id)
      .subscribe(response => {
        this.errorMessage=response;
      })
  }

}
