import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Cinema} from '../shared/cinema.model';
import {CinemaService} from '../shared/cinema.service';

@Component({
  selector: 'app-filter-cinema',
  templateUrl: './filter-cinema.component.html',
  styleUrls: ['./filter-cinema.component.css']
})
export class FilterCinemaComponent implements OnInit {
  cinemaForm: FormGroup;
  cinemas: Cinema[] = [];
  filteredCinemas: Cinema[] = [];

  constructor(private cinemaService: CinemaService) { }

  ngOnInit(): void {
    this.cinemaForm = new FormGroup({
      seats: new FormControl('', [
        Validators.required,
        Validators.min(1)
      ])
    });

    this.getCinemas();
  }

  get seats() { return this.cinemaForm.get('seats'); }

  getCinemas(): void {
    this.cinemaService.getCinemas()
      .subscribe(response => this.cinemas = response);
  }

  filterCinemas(seats: number): void {
    this.cinemaService.getCinemasByNumberOfSeats(seats)
      .subscribe(response => this.filteredCinemas = response);
  }

}
