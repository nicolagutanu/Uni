import { Injectable } from '@angular/core';
import { Movie } from "./movies";
import { Observable, of } from 'rxjs';
import { HttpClient, HttpErrorResponse, HttpParams, HttpHeaders } from '@angular/common/http';
import {map} from "rxjs/operators";


@Injectable({
  providedIn: 'root'
})
export class MovieServiceService {
  private backendURL = 'http://localhost:8080/api/get-all-movies';

  constructor(private http: HttpClient) { }

  getAllMovies(): Observable<Movie[]> {
    console.log(this.http.get<Movie[]>(this.backendURL));
    return this.http.get<Movie[]>(this.backendURL);
  }
}
