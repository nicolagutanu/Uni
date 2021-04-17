package ro.ubb.springjpa.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.springjpa.model.Movie;
import ro.ubb.springjpa.repository.MovieRepository;

import java.util.List;

/**
 * Created by les aristocats.
 */
@Service
public class MovieServiceImpl implements MovieService {
    public static final Logger log = LoggerFactory.getLogger(MovieServiceImpl.class);

    @Autowired
    private MovieRepository movieRepository;


    @Override
    public List<Movie> getAllMovies() {
        log.trace("getAllMovies --- method entered");

        List<Movie> result = movieRepository.findAll();

        log.trace("getAllMovies: result={}", result);

        return result;
    }

    @Override
    public void saveMovie(Movie movie) {
        //todo: for each method, log at least: the fact that the method was entered and finished, input data (if any), returned values (if any)
        movieRepository.save(movie);
    }

    @Override
    @Transactional
    public void updateMovie(Movie movie) {
        log.trace("updateMovie - method entered: movie={}", movie);

        movieRepository.findById(movie.getId())
                .ifPresent(s -> {
                    s.setName(movie.getName());
                    s.setRating(movie.getRating());
                    log.debug("updateMovie - updated: s={}", s);
                });

        log.trace("updateMovie - method finished");
    }

    @Override
    public void deleteMovieById(Integer id) {
        //todo: logs
        movieRepository.deleteById(id);
    }
}
