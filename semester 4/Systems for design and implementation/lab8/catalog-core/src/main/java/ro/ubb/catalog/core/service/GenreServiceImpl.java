package ro.ubb.catalog.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.catalog.core.model.Genre;
import ro.ubb.catalog.core.repository.GenreRepository;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {
    @Autowired
    private GenreRepository genreRepository;

    @Override
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    @Override
    public Genre saveGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    @Override
    @Transactional
    public Genre updateGenre(Genre genre) {
        Genre updateGenre = genreRepository.findById(genre.getId()).orElseThrow();
        updateGenre.setName(genre.getName());
        return genre;
    }

    @Override
    public void deleteGenre(Integer id) {
        genreRepository.deleteById(id);
    }
}
