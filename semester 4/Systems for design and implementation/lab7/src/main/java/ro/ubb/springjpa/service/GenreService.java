package ro.ubb.springjpa.service;

import ro.ubb.springjpa.model.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> getAllGenres();

    void saveGenre(Genre genre);

    void updateGenre(Genre genre);

    void deleteGenreById(Integer id);
}
