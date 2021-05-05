package ro.ubb.catalog.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.catalog.core.model.Cinema;
import ro.ubb.catalog.core.repository.CinemaRepository;

import java.util.List;

@Service
public class CinemaServiceImpl implements CinemaService {
    @Autowired
    private CinemaRepository cinemaRepository;

    @Override
    public List<Cinema> getAllCinemas() {
        return cinemaRepository.findAll();
    }

    @Override
    public Cinema saveCinema(Cinema cinema) {
        return cinemaRepository.save(cinema);
    }

    @Override
    @Transactional
    public Cinema updateCinema(Cinema cinema) {
        Cinema updateCinema = cinemaRepository.findById(cinema.getId()).orElseThrow();
        updateCinema.setName(cinema.getName());
        updateCinema.setNrOfSeats(cinema.getNrOfSeats());
        return cinema;
    }

    @Override
    public void deleteCinema(Integer id) {
        cinemaRepository.deleteById(id);
    }

    @Override
    public List<Cinema> getCinemasByNrOfSeats(Integer nrOfSeats) {
        return cinemaRepository.getCinemasByNrOfSeats(nrOfSeats);
    }
}
