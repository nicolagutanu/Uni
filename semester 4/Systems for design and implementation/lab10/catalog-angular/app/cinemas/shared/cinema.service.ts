import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Cinema} from './cinema.model';

@Injectable({
  providedIn: 'root'
})
export class CinemaService {

  private cinemaURL = 'http://localhost:8080/api/';

  constructor(private httpClient: HttpClient) { }

  getCinemas(): Observable<Cinema[]> {
    return this.httpClient.get<Cinema[]>(this.cinemaURL + 'get-all-cinemas');
  }

  getCinemasByNumberOfSeats(nrOfSeats: number): Observable<Cinema[]> {
    return this.httpClient.get<Cinema[]>(this.cinemaURL + 'cinemas-by-nrofseats/' + nrOfSeats);
  }

  getSortedCinemas(): Observable<Cinema[]> {
    return this.httpClient.get<Cinema[]>(this.cinemaURL + 'sort-cinemas-by-nrofseats');
  }
}
