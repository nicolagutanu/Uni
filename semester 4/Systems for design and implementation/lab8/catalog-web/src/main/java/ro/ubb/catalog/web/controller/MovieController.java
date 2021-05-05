package ro.ubb.catalog.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.catalog.core.service.MovieService;
import ro.ubb.catalog.web.converter.MovieConverter;
import ro.ubb.catalog.web.dto.MovieDto;
import ro.ubb.catalog.web.dto.MoviesDto;

@RestController
public class MovieController {
    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieConverter movieConverter;

    @RequestMapping(value="/get-all-movies")
    MoviesDto getAllMovies() {
        return new MoviesDto(
                movieConverter.convertModelsToDtos(
                        movieService.getAllMovies()));
    }

    @RequestMapping(value="/add-movie", method= RequestMethod.POST)
    MovieDto addMovie(@RequestBody MovieDto movieDto) {
        var movie = movieConverter.convertDtoToModel(movieDto);
        var result = movieService.saveMovie(movie);
        var resultModel = movieConverter.convertModelToDto(result);
        return resultModel;
    }

    @RequestMapping(value="/update-movie/{id}", method = RequestMethod.PUT)
    MovieDto updateMovie(@PathVariable Integer id, @RequestBody MovieDto dto) {
        return movieConverter.convertModelToDto(
                movieService.updateMovie(
                        movieConverter.convertDtoToModel(dto)));
    }

    @RequestMapping(value="/delete-movie/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteMovie(@PathVariable Integer id) {
        movieService.deleteMovie(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/movies-by-rating/{rating}")
    MoviesDto getMoviesByRating(@PathVariable Float rating) {
        return new MoviesDto(
                movieConverter.convertModelsToDtos(
                        movieService.getMoviesByRating(rating)));
    }
}
