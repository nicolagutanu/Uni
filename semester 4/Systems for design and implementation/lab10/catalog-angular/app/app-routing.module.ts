import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AddMovieComponent} from './movies/add-movie/add-movie.component';
import {UpdateMovieComponent} from './movies/update-movie/update-movie.component';
import {DeleteMovieComponent} from './movies/delete-movie/delete-movie.component';
import {ListMovieComponent} from './movies/list-movie/list-movie.component';
import {FilterGenreComponent} from './genres/filter-genre/filter-genre.component';
import {SortGenreComponent} from './genres/sort-genre/sort-genre.component';
import {FilterCinemaComponent} from './cinemas/filter-cinema/filter-cinema.component';
import {SortCinemaComponent} from './cinemas/sort-cinema/sort-cinema.component';
import {AppComponent} from './app.component';

const routes: Routes = [
  {path: 'add-movie', component: AddMovieComponent},
  {path: 'update-movie', component: UpdateMovieComponent},
  {path: 'delete-movie', component: DeleteMovieComponent},
  {path: 'list-movies', component: ListMovieComponent},
  {path: 'filter-genres', component: FilterGenreComponent},
  {path: 'sort-genres', component: SortGenreComponent},
  {path: 'filter-cinemas', component: FilterCinemaComponent},
  {path: 'sort-cinemas', component: SortCinemaComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
