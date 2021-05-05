package ro.ubb.catalog.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.catalog.core.service.GenreService;
import ro.ubb.catalog.web.converter.GenreConverter;
import ro.ubb.catalog.web.dto.GenreDto;
import ro.ubb.catalog.web.dto.GenresDto;

@RestController
public class GenreController {
    @Autowired
    private GenreService genreService;

    @Autowired
    private GenreConverter genreConverter;

    @RequestMapping(value="/get-all-genres")
    GenresDto getAllGenres() {
        return new GenresDto(
                genreConverter.convertModelsToDtos(
                        genreService.getAllGenres()));
    }

    @RequestMapping(value="/add-genre", method= RequestMethod.POST)
    GenreDto addGenre(@RequestBody GenreDto genreDto) {
        var genre = genreConverter.convertDtoToModel(genreDto);
        var result = genreService.saveGenre(genre);
        var resultModel = genreConverter.convertModelToDto(result);
        return resultModel;
    }

    @RequestMapping(value="/update-genre/{id}", method = RequestMethod.PUT)
    GenreDto updateGenre(@PathVariable Integer id, @RequestBody GenreDto dto) {
        return genreConverter.convertModelToDto(
                genreService.updateGenre(
                        genreConverter.convertDtoToModel(dto)));
    }

    @RequestMapping(value="/delete-genre/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteGenre(@PathVariable Integer id) {
        genreService.deleteGenre(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
