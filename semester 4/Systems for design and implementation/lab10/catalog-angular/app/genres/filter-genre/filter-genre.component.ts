import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {GenreService} from '../shared/genre.service';
import {Genre} from '../shared/genre.model';

@Component({
  selector: 'app-filter-genre',
  templateUrl: './filter-genre.component.html',
  styleUrls: ['./filter-genre.component.css']
})
export class FilterGenreComponent implements OnInit {
  genreForm: FormGroup;
  genres: Genre[] = [];
  filteredGenres: Genre[] = [];

  constructor(private genreService: GenreService) { }

  ngOnInit(): void {
    this.genreForm = new FormGroup({
      name: new FormControl('', [
        Validators.required,
        Validators.minLength(3)
      ])
    });
    this.getGenres();
  }

  get name() { return this.genreForm.get('name'); }

  filterGenres(name: string): void {
    this.filteredGenres = this.genres.filter(genre => genre.name === name);
  }

  getGenres(): void {
    this.genreService.getGenres()
      .subscribe(response => this.genres = response);
  }
}
