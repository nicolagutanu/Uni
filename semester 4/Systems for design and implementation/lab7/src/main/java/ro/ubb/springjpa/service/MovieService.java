package ro.ubb.springjpa.service;

import ro.ubb.springjpa.model.Movie;

import java.util.List;

/**
 * Created by les aristocats.
 */
public interface MovieService {
    List<Movie> getAllMovies();

    void saveMovie(Movie movie);

    void updateMovie(Movie movie);

    void deleteMovieById(Integer id);
}
