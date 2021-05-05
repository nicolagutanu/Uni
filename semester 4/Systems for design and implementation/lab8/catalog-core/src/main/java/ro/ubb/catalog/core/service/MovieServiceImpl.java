package ro.ubb.catalog.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.catalog.core.model.Movie;
import ro.ubb.catalog.core.repository.MovieRepository;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    @Transactional
    public Movie updateMovie(Movie movie) {
        Movie updateMovie = movieRepository.findById(movie.getId()).orElseThrow();
        updateMovie.setName(movie.getName());
        updateMovie.setRating(movie.getRating());
        return movie;
    }

    @Override
    public void deleteMovie(Integer id) {
        movieRepository.deleteById(id);
    }

    @Override
    public List<Movie> getMoviesByRating(Float rating) {
        return movieRepository.getMoviesByRating(rating);
    }
}
