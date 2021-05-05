import { Injectable } from '@angular/core';
import { Recipe } from "./recipe";
import { MockRecipes } from "./mock-recipes";
import { catchError, map, tap } from 'rxjs/operators';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpErrorResponse, HttpParams, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ServiceRecipeService {
  private backendUrlFilter='http://localhost/web/lab6_php/page5/page5_php2.php';
  private backendUrlFilterByType='http://localhost/web/lab6_php/page5/page5_php.php';
  private backendUrlAdd='http://localhost/web/lab6_php/page2/page2_php.php';
  private backendUrlDelete='http://localhost/web/lab6_php/page4/page4_php.php';
  private backendUrlUpdate='http://localhost/web/lab6_php/page3/page3_php.php';
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type' : 'application/json'
    })
  };

  constructor(private http: HttpClient) { }

  getRecipes(): Observable<Recipe[]> {
    return this.http.get<Recipe[]>(this.backendUrlFilter);
  }

  getRecipeByType(type): Observable<Recipe[]> {
    return this.http.get<Recipe[]>(this.backendUrlFilterByType+"?type="+type);
  }

  AddRecipe(recipe: Recipe): Observable<string> {
    return this.http.get<string>(this.backendUrlAdd+"?id="+recipe.id+"&author="+recipe.author+"&name="+recipe.name+"&type="+recipe.type+"&desc="+recipe.desc);
  }

  DeleteRecipe(id): Observable<string> {
    return this.http.get<string>(this.backendUrlDelete+"?id="+id);
  }

  UpdateRecipe(recipe: Recipe): Observable<string> {
    return this.http.get<string>(this.backendUrlUpdate+"?id="+recipe.id+"&author="+recipe.author+"&name="+recipe.name+"&type="+recipe.type+"&desc="+recipe.desc);
  }
}
