import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MoviesComponent } from './movies/movies.component';
import { AddMovieComponent } from './movies/add-movie/add-movie.component';
import { DeleteMovieComponent } from './movies/delete-movie/delete-movie.component';
import { UpdateMovieComponent } from './movies/update-movie/update-movie.component';
import { ListMovieComponent } from './movies/list-movie/list-movie.component';
import { CinemasComponent } from './cinemas/cinemas.component';
import { FilterCinemaComponent } from './cinemas/filter-cinema/filter-cinema.component';
import { SortCinemaComponent } from './cinemas/sort-cinema/sort-cinema.component';
import { GenresComponent } from './genres/genres.component';
import { SortGenreComponent } from './genres/sort-genre/sort-genre.component';
import { FilterGenreComponent } from './genres/filter-genre/filter-genre.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {MovieService} from './movies/shared/movie.service';
import {CinemaService} from './cinemas/shared/cinema.service';
import {GenreService} from './genres/shared/genre.service';

@NgModule({
  declarations: [
    AppComponent,
    MoviesComponent,
    AddMovieComponent,
    DeleteMovieComponent,
    UpdateMovieComponent,
    ListMovieComponent,
    CinemasComponent,
    FilterCinemaComponent,
    SortCinemaComponent,
    GenresComponent,
    SortGenreComponent,
    FilterGenreComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [MovieService, CinemaService, GenreService],
  bootstrap: [AppComponent]
})
export class AppModule { }
