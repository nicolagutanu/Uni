import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Movie } from './movie.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MovieService {
  private movieURL = 'http://localhost:8080/api/';

  constructor(private httpClient: HttpClient) { }

  getMovies(): Observable<Movie[]> {
    return this.httpClient.get<Movie[]>(this.movieURL + 'get-all-movies');
  }

  addMovie(movie: Movie): void {
    this.httpClient.post<HttpResponse<any>>(this.movieURL + 'add-movie', movie)
      .subscribe(response => console.log(response));
  }

  updateMovie(movie: Movie): void {
    this.httpClient.put<HttpResponse<any>>(this.movieURL + 'update-movie/' + movie.id, movie)
      .subscribe(response => console.log(response));
  }

  deleteMovie(movieId: number): void {
    this.httpClient.delete(this.movieURL + 'delete-movie/' + movieId)
      .subscribe(response => console.log(response));
  }
}
