import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {MovieService} from '../shared/movie.service';

@Component({
  selector: 'app-delete-movie',
  templateUrl: './delete-movie.component.html',
  styleUrls: ['./delete-movie.component.css']
})
export class DeleteMovieComponent implements OnInit {

  movieForm: FormGroup;

  constructor(private movieService: MovieService) { }

  ngOnInit(): void {
    this.movieForm = new FormGroup({
      id: new FormControl('', [
        Validators.required,
        Validators.min(1)
      ])
    });
  }

  get id() { return this.movieForm.get('id'); }

  deleteMovie(id: number): void {
    this.movieService.deleteMovie(id);
  }

}
