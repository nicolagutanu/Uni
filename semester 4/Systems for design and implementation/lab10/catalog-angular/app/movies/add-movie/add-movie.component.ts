import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MovieService } from '../shared/movie.service';
import {Movie} from '../shared/movie.model';

@Component({
  selector: 'app-add-movie',
  templateUrl: './add-movie.component.html',
  styleUrls: ['./add-movie.component.css']
})
export class AddMovieComponent implements OnInit {
  movieForm: FormGroup;

  constructor(private movieService: MovieService) { }

  ngOnInit(): void {
    this.movieForm = new FormGroup({
      name: new FormControl('', [
        Validators.required,
        Validators.minLength(3)
      ]),
      rating: new FormControl('', [
        Validators.required,
        Validators.min(0),
        Validators.max(10)
      ]),
      id: new FormControl('', [
        Validators.required,
        Validators.min(1)
      ])
    });
  }

  get name() { return this.movieForm.get('name'); }

  get rating() { return this.movieForm.get('rating'); }

  get id() { return this.movieForm.get('id'); }

  addMovie(id: number, name: string, rating: number): void {
    this.movieService.addMovie({
      id,
      name,
      rating
    });
  }

}
