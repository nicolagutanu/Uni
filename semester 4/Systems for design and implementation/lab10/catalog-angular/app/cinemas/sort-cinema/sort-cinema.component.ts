import { Component, OnInit } from '@angular/core';
import {Cinema} from '../shared/cinema.model';
import {CinemaService} from '../shared/cinema.service';

@Component({
  selector: 'app-sort-cinema',
  templateUrl: './sort-cinema.component.html',
  styleUrls: ['./sort-cinema.component.css']
})
export class SortCinemaComponent implements OnInit {
  cinemas: Cinema[] = [];

  constructor(private cinemaService: CinemaService) { }

  ngOnInit(): void {
    this.getCinemas();
  }

  sortCinemas(): void {
    this.cinemaService.getSortedCinemas()
      .subscribe(response => this.cinemas = response);
  }

  getCinemas(): void {
    this.cinemaService.getCinemas()
      .subscribe(response => this.cinemas = response);
  }

}
