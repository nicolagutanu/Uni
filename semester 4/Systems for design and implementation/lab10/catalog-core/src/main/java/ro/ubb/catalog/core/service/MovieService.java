package ro.ubb.catalog.core.service;

import ro.ubb.catalog.core.model.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getAllMovies();

    Movie saveMovie(Movie movie);

    Movie updateMovie(Movie movie);

    void deleteMovie(Integer id);

    List<Movie> getMoviesByRating(Float rating);
}
