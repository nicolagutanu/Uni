package ro.ubb.catalog.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.catalog.core.model.Movie;
import ro.ubb.catalog.core.service.MovieService;
import ro.ubb.catalog.web.converter.MovieConverter;
import ro.ubb.catalog.web.dto.MovieDto;
import ro.ubb.catalog.web.dto.MoviesDto;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MovieController {
    public static final Logger log = LoggerFactory.getLogger(MovieController.class);

    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieConverter movieConverter;

    @RequestMapping(value="/get-all-movies")
    List<MovieDto> getAllMovies() {
        log.trace("getAllMovies controller --- method entered");
        List<Movie> movies = movieService.getAllMovies();
        log.trace("getAllMovies controller --- method finished: result={}", movies);
        return new ArrayList<>(movieConverter.convertModelsToDtos(movies));
    }

    @RequestMapping(value="/add-movie", method= RequestMethod.POST)
    MovieDto addMovie(@RequestBody MovieDto movieDto) {
        log.trace("addMovie controller --- method entered");
        var movie = movieConverter.convertDtoToModel(movieDto);
        var result = movieService.saveMovie(movie);
        var resultModel = movieConverter.convertModelToDto(result);
        log.trace("addMovie controller --- method finished: result={}", resultModel);
        return resultModel;
    }

    @RequestMapping(value="/update-movie/{id}", method = RequestMethod.PUT)
    MovieDto updateMovie(@PathVariable Integer id, @RequestBody MovieDto dto) {
        log.trace("updateMovie controller --- method entered");
        MovieDto result = movieConverter.convertModelToDto(
                movieService.updateMovie(
                        movieConverter.convertDtoToModel(dto)));
        log.trace("updateMovie controller --- method finished, result={}", result);
        return result;
    }

    @RequestMapping(value="/delete-movie/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteMovie(@PathVariable Integer id) {
        log.trace("deleteMovie controller --- method entered");
        movieService.deleteMovie(id);
        log.trace("deleteMovie controller --- method finished");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/movies-by-rating/{rating}")
    MoviesDto getMoviesByRating(@PathVariable Float rating) {
        log.trace("getMoviesByRating controller --- method entered");
        MoviesDto result = new MoviesDto(
                movieConverter.convertModelsToDtos(
                        movieService.getMoviesByRating(rating)));
        log.trace("getMoviesByRating controller --- method finished: result={}", result);
        return result;
    }
}
