package ro.ubb.catalog.core.service;

import ro.ubb.catalog.core.model.Cinema;

import java.util.List;

public interface CinemaService {
    List<Cinema> getAllCinemas();

    Cinema saveCinema(Cinema cinema);

    Cinema updateCinema(Cinema cinema);

    void deleteCinema(Integer id);

    List<Cinema> getCinemasByNrOfSeats(Integer nrOfSeats);
}
