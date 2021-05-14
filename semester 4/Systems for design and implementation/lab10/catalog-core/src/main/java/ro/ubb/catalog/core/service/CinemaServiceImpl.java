package ro.ubb.catalog.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.catalog.core.model.Cinema;
import ro.ubb.catalog.core.repository.CinemaRepository;

import java.util.List;

@Service
public class CinemaServiceImpl implements CinemaService {
    public static final Logger log = LoggerFactory.getLogger(CinemaServiceImpl.class);

    @Autowired
    private CinemaRepository cinemaRepository;

    @Override
    public List<Cinema> getAllCinemas() {
        log.trace("getAllCinemas --- method entered");
        List<Cinema> cinemas = cinemaRepository.findAll();
        log.trace("getAllCinemas --- method finished: result={}", cinemas);
        return cinemas;
    }

    @Override
    public Cinema saveCinema(Cinema cinema) {
        log.trace("saveCinema --- method entered");
        Cinema result = cinemaRepository.save(cinema);
        log.trace("saveCinema --- method finished: result={}", result);
        return result;
    }

    @Override
    @Transactional
    public Cinema updateCinema(Cinema cinema) {
        log.trace("updateCinema --- method entered");
        Cinema updateCinema = cinemaRepository.findById(cinema.getId()).orElseThrow();
        updateCinema.setName(cinema.getName());
        updateCinema.setNrOfSeats(cinema.getNrOfSeats());
        log.trace("updateCinema --- method finished: result={}", updateCinema);
        return cinema;
    }

    @Override
    public void deleteCinema(Integer id) {
        log.trace("deleteCinema --- method entered");
        cinemaRepository.deleteById(id);
        log.trace("deleteCinema --- method finished");
    }

    @Override
    public List<Cinema> getCinemasByNrOfSeats(Integer nrOfSeats) {
        log.trace("getCinemasByNrOfSeats --- method entered");
        List<Cinema> cinemas = cinemaRepository.getCinemasByNrOfSeats(nrOfSeats);
        log.trace("getCinemasByNrOfSeats --- method finished: result={}", cinemas);
        return cinemas;
    }

    @Override
    public List<Cinema> sortCinemasByNrOfSeats() {
        log.trace("sortCinemasByNrOfSeats --- method entered");
        List<Cinema> cinemas = cinemaRepository.sortCinemasByNrOfSeats();
        log.trace("sortCinemasByNrOfSeats --- method finished: result={}", cinemas);
        return cinemas;
    }
}
