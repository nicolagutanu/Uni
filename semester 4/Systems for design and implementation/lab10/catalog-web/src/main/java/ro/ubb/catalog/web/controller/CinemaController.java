package ro.ubb.catalog.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.catalog.core.model.Cinema;
import ro.ubb.catalog.core.service.CinemaService;
import ro.ubb.catalog.web.converter.CinemaConverter;
import ro.ubb.catalog.web.dto.CinemaDto;
import ro.ubb.catalog.web.dto.CinemasDto;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CinemaController {
    public static final Logger log = LoggerFactory.getLogger(CinemaController.class);

    @Autowired
    private CinemaService cinemaService;

    @Autowired
    private CinemaConverter cinemaConverter;

    @RequestMapping(value="/get-all-cinemas")
    List<CinemaDto> getAllCinemas() {
        log.trace("getAllCinemas controller --- method entered");
        List<Cinema> cinemas = cinemaService.getAllCinemas();
        log.trace("getAllCinemas controller --- method finished: result={}", cinemas);
        return new ArrayList<>(cinemaConverter.convertModelsToDtos(cinemas));
    }

    @RequestMapping(value="/add-cinema", method = RequestMethod.POST)
    CinemaDto addCinema(@RequestBody CinemaDto cinemaDto) {
        log.trace("addCinema controller --- method entered");
        var cinema = cinemaConverter.convertDtoToModel(cinemaDto);
        var result = cinemaService.saveCinema(cinema);
        var resultModel = cinemaConverter.convertModelToDto(result);
        log.trace("addCinema controller --- method finished: result={}", resultModel);
        return resultModel;
    }

    @RequestMapping(value = "/update-cinema/{id}", method = RequestMethod.PUT)
    CinemaDto updateCinema(@PathVariable Integer id, @RequestBody CinemaDto dto) {
        log.trace("updateCinema controller --- method entered");
        CinemaDto result = cinemaConverter.convertModelToDto(
                cinemaService.updateCinema(
                        cinemaConverter.convertDtoToModel(dto)));
        log.trace("updateCinema controller --- method finished: result={}", result);
        return result;
    }

    @RequestMapping(value = "/delete-cinema/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteCinema(@PathVariable Integer id) {
        log.trace("deleteCinema controller --- method entered");
        cinemaService.deleteCinema(id);
        log.trace("deleteCinema controller --- method finished");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/cinemas-by-nrofseats/{nrofseats}")
    List<CinemaDto> getCinemasByNrOfSeats(@PathVariable Integer nrofseats) {
        log.trace("getCinemasByNrOfSeats controller --- method entered");
        List<Cinema> cinemas = cinemaService.getCinemasByNrOfSeats(nrofseats);
        log.trace("getCinemasByNrOfSeats controller --- method finished: result={}", cinemas);
        return new ArrayList<>(cinemaConverter.convertModelsToDtos(cinemas));
    }

    @RequestMapping(value = "/sort-cinemas-by-nrofseats")
    List<CinemaDto> sortCinemasByNrOfSeats() {
        log.trace("sortCinemasByNrOfSeats controller --- method entered");
        List<Cinema> cinemas = cinemaService.sortCinemasByNrOfSeats();
        log.trace("sortCinemasByNrOfSeats controller --- method finished: result={}", cinemas);
        return new ArrayList<>(cinemaConverter.convertModelsToDtos(cinemas));
    }
}
