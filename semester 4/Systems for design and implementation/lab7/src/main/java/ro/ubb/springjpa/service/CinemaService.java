package ro.ubb.springjpa.service;

import ro.ubb.springjpa.model.Cinema;

import java.util.List;

public interface CinemaService {
    List<Cinema> getAllCinemas();

    void saveCinema(Cinema cinema);

    void updateCinema(Cinema cinema);

    void deleteCinemaById(Integer id);
}