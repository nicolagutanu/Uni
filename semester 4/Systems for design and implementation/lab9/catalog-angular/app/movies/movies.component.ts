import { Component, OnInit } from '@angular/core';
import { Movie } from "../movies";
import { MovieServiceService } from "../movie-service.service";

@Component({
  selector: 'app-movies',
  templateUrl: './movies.component.html',
  styleUrls: ['./movies.component.css']
})
export class MoviesComponent implements OnInit {
  movies: Movie[] = [];
  errorMessage: string;

  constructor(private movieService: MovieServiceService) {
    this.movies = [];
    this.errorMessage = '';
  }

  ngOnInit(): void {
    this.getMovies();
  }

  getMovies(): void {
    this.movieService.getAllMovies()
      .subscribe(movies => {this.movies = movies},
        error => this.errorMessage = <any>error);
    console.log(this.movies);
  }
}
