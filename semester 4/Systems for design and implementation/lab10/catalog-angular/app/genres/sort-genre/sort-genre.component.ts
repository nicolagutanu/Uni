import { Component, OnInit } from '@angular/core';
import {Genre} from '../shared/genre.model';
import {GenreService} from '../shared/genre.service';

@Component({
  selector: 'app-sort-genre',
  templateUrl: './sort-genre.component.html',
  styleUrls: ['./sort-genre.component.css']
})
export class SortGenreComponent implements OnInit {
  genres: Genre[] = [];

  constructor(private genreService: GenreService) { }

  ngOnInit(): void {
    this.getGenres();
  }

  sortGenres(): void {
    this.genres = this.genres.sort((g1, g2) => g1.name < g2.name ? -1 : 1);
  }

  getGenres(): void {
    this.genreService.getGenres()
      .subscribe(response => this.genres = response);
  }
}
