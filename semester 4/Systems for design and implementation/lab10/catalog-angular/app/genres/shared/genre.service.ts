import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Genre} from './genre.model';

@Injectable({
  providedIn: 'root'
})
export class GenreService {
  private genreURL = 'http://localhost:8080/api/';

  constructor(private httpClient: HttpClient) { }

  getGenres(): Observable<Genre[]> {
    return this.httpClient.get<Genre[]>(this.genreURL + 'get-all-genres');
  }
}
