package ro.ubb.catalog.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.catalog.core.model.Genre;
import ro.ubb.catalog.core.service.GenreService;
import ro.ubb.catalog.web.converter.GenreConverter;
import ro.ubb.catalog.web.dto.GenreDto;
import ro.ubb.catalog.web.dto.GenresDto;

import java.util.ArrayList;
import java.util.List;

@RestController
public class GenreController {
    public static final Logger log = LoggerFactory.getLogger(GenreController.class);

    @Autowired
    private GenreService genreService;

    @Autowired
    private GenreConverter genreConverter;

    @RequestMapping(value="/get-all-genres")
    List<GenreDto> getAllGenres() {
        log.trace("getAllGenres controller --- method entered");
        List<Genre> genres = genreService.getAllGenres();
        log.trace("getAllGenres controller --- method finished: result={}", genres);
        return new ArrayList<>(genreConverter.convertModelsToDtos(genres));
    }

    @RequestMapping(value="/add-genre", method= RequestMethod.POST)
    GenreDto addGenre(@RequestBody GenreDto genreDto) {
        log.trace("addGenre controller --- method entered");
        var genre = genreConverter.convertDtoToModel(genreDto);
        var result = genreService.saveGenre(genre);
        var resultModel = genreConverter.convertModelToDto(result);
        log.trace("addGenre controller --- method finished: result={}", resultModel);
        return resultModel;
    }

    @RequestMapping(value="/update-genre/{id}", method = RequestMethod.PUT)
    GenreDto updateGenre(@PathVariable Integer id, @RequestBody GenreDto dto) {
        log.trace("updateGenre controller --- method entered");
        GenreDto result = genreConverter.convertModelToDto(
                genreService.updateGenre(
                        genreConverter.convertDtoToModel(dto)));
        log.trace("updateGenre controller --- method finished: result={}", result);
        return result;
    }

    @RequestMapping(value="/delete-genre/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteGenre(@PathVariable Integer id) {
        log.trace("deleteGenre controller --- method entered");
        genreService.deleteGenre(id);
        log.trace("deleteGenre controller --- method finished");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
