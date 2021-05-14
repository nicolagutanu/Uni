package ro.ubb.catalog.core.service;

import ro.ubb.catalog.core.model.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> getAllGenres();

    Genre saveGenre(Genre genre);

    Genre updateGenre(Genre genre);

    void deleteGenre(Integer id);
}
