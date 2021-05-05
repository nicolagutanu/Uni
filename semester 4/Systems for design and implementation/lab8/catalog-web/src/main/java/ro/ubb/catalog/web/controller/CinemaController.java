package ro.ubb.catalog.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.catalog.core.service.CinemaService;
import ro.ubb.catalog.web.converter.CinemaConverter;
import ro.ubb.catalog.web.dto.CinemaDto;
import ro.ubb.catalog.web.dto.CinemasDto;

@RestController
public class CinemaController {
    @Autowired
    private CinemaService cinemaService;

    @Autowired
    private CinemaConverter cinemaConverter;

    @RequestMapping(value="/get-all-cinemas")
    CinemasDto getAllCinemas() {
        return new CinemasDto(
                cinemaConverter.convertModelsToDtos(
                        cinemaService.getAllCinemas()));
    }

    @RequestMapping(value="/add-cinema", method = RequestMethod.POST)
    CinemaDto addCinema(@RequestBody CinemaDto cinemaDto) {
        var cinema = cinemaConverter.convertDtoToModel(cinemaDto);
        var result = cinemaService.saveCinema(cinema);
        var resultModel = cinemaConverter.convertModelToDto(result);
        return resultModel;
    }

    @RequestMapping(value = "/update-cinema/{id}", method = RequestMethod.PUT)
    CinemaDto updateCinema(@PathVariable Integer id, @RequestBody CinemaDto dto) {
        return cinemaConverter.convertModelToDto(
                cinemaService.updateCinema(
                        cinemaConverter.convertDtoToModel(dto)));
    }

    @RequestMapping(value = "/delete-cinema/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteCinema(@PathVariable Integer id) {
        cinemaService.deleteCinema(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/cinemas-by-nrofseats/{nrofseats}")
    CinemasDto getCinemasByNrOfSeats(@PathVariable Integer nrofseats) {
        return new CinemasDto(
                cinemaConverter.convertModelsToDtos(
                        cinemaService.getCinemasByNrOfSeats(nrofseats)));
    }
}
