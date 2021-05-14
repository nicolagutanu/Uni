package ro.ubb.catalog.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.catalog.core.model.Movie;
import ro.ubb.catalog.core.repository.MovieRepository;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    public static final Logger log = LoggerFactory.getLogger(MovieServiceImpl.class);

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public List<Movie> getAllMovies() {
        log.trace("getAllMovies --- method entered");
        List<Movie> movies = movieRepository.findAll();
        log.trace("getAllMovies --- method finished: result={}", movies);
        return movies;
    }

    @Override
    public Movie saveMovie(Movie movie) {
        log.trace("saveMovie --- method entered");
        Movie result = movieRepository.save(movie);
        log.trace("saveMovie --- method finished: result={}", result);
        return result;
    }

    @Override
    @Transactional
    public Movie updateMovie(Movie movie) {
        log.trace("updateMovie --- method entered");
        Movie updateMovie = movieRepository.findById(movie.getId()).orElseThrow();
        updateMovie.setName(movie.getName());
        updateMovie.setRating(movie.getRating());
        log.trace("updateMovie --- method finished: result={}", updateMovie);
        return movie;
    }

    @Override
    public void deleteMovie(Integer id) {
        log.trace("deleteMovie --- method entered");
        movieRepository.deleteById(id);
        log.trace("deleteMovie --- method finished");
    }

    @Override
    public List<Movie> getMoviesByRating(Float rating) {
        log.trace("getMoviesByRating --- method entered");
        List<Movie> movies = movieRepository.getMoviesByRating(rating);
        log.trace("getAMoviesByRating --- method finished: result={}", movies);
        return movies;
    }
}
