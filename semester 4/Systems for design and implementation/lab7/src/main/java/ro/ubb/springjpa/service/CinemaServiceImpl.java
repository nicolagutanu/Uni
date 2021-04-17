package ro.ubb.springjpa.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.springjpa.model.Cinema;
import ro.ubb.springjpa.repository.CinemaRepository;

import java.util.List;

@Service
public class CinemaServiceImpl implements CinemaService {
    public static final Logger log = LoggerFactory.getLogger(CinemaServiceImpl.class);

    @Autowired
    private CinemaRepository cinemaRepository;

    @Override
    public List<Cinema> getAllCinemas() {
        log.trace("getAllCinemas --- method entered");

        List<Cinema> result = cinemaRepository.findAll();

        log.trace("getAllCinemas: result={}", result);

        return result;
    }

    @Override
    public void saveCinema(Cinema cinema) {
        //todo: for each method, log at least: the fact that the method was entered and finished, input data (if any), returned values (if any)
        cinemaRepository.save(cinema);
    }

    @Override
    @Transactional
    public void updateCinema(Cinema cinema) {
        log.trace("updateCinema - method entered: cinema={}", cinema);

        cinemaRepository.findById(cinema.getId())
                .ifPresent(s -> {
                    s.setName(cinema.getName());
                    s.setNrOfSeats(cinema.getNrOfSeats());
                    log.debug("updateCinema - updated: s={}", s);
                });

        log.trace("updateCinema - method finished");
    }

    @Override
    public void deleteCinemaById(Integer id) {
        //todo: logs
        cinemaRepository.deleteById(id);
    }
}
