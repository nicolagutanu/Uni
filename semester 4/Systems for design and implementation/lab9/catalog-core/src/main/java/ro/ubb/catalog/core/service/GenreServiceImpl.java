package ro.ubb.catalog.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.catalog.core.model.Genre;
import ro.ubb.catalog.core.repository.GenreRepository;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {
    public static final Logger log = LoggerFactory.getLogger(GenreServiceImpl.class);

    @Autowired
    private GenreRepository genreRepository;

    @Override
    public List<Genre> getAllGenres() {
        log.trace("getAllGenres --- method entered");
        List<Genre> genres = genreRepository.findAll();
        log.trace("getAllGenres --- method finished, result={}", genres);
        return genres;
    }

    @Override
    public Genre saveGenre(Genre genre) {
        log.trace("saveGenre --- method entered");
        Genre result = genreRepository.save(genre);
        log.trace("saveGenre --- method finished: result={}", result);
        return result;
    }

    @Override
    @Transactional
    public Genre updateGenre(Genre genre) {
        log.trace("updateGenre --- method entered");
        Genre updateGenre = genreRepository.findById(genre.getId()).orElseThrow();
        updateGenre.setName(genre.getName());
        log.trace("updateGenre --- method finished: result={}", updateGenre);
        return genre;
    }

    @Override
    public void deleteGenre(Integer id) {
        log.trace("deleteGenre --- method entered");
        genreRepository.deleteById(id);
        log.trace("deleteGenre --- method finished");
    }
}
