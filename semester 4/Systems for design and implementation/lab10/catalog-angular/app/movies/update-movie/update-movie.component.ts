import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {MovieService} from '../shared/movie.service';

@Component({
  selector: 'app-update-movie',
  templateUrl: './update-movie.component.html',
  styleUrls: ['./update-movie.component.css']
})
export class UpdateMovieComponent implements OnInit {

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

  updateMovie(id: number, name: string, rating: number): void {
    this.movieService.updateMovie({
      id,
      name,
      rating
    });
  }

}
