package ro.ubb.springjpa.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.springjpa.model.Genre;
import ro.ubb.springjpa.repository.GenreRepository;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {
    public static final Logger log = LoggerFactory.getLogger(GenreServiceImpl.class);

    @Autowired
    private GenreRepository genreRepository;

    @Override
    public List<Genre> getAllGenres() {
        log.trace("getAllGenre --- method entered");

        List<Genre> result = genreRepository.findAll();

        log.trace("getAllGenres: result={}", result);

        return result;
    }

    @Override
    public void saveGenre(Genre genre) {
        genreRepository.save(genre);
    }

    @Override
    @Transactional
    public void updateGenre(Genre genre) {
        log.trace("updateGenre - method entered: genre={}", genre);

        genreRepository.findById(genre.getId())
                .ifPresent(s -> {
                    s.setName(genre.getName());
                    log.debug("updateGenre - updated: s={}", s);
                });

        log.trace("updateGenre - method finished");
    }

    @Override
    public void deleteGenreById(Integer id) {
        genreRepository.deleteById(id);
    }
}
