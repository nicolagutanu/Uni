package service;

import domain.Catalog;
import domain.Cinema;
import domain.Genre;
import domain.validators.GenreValidator;
import domain.validators.ValidatorException;
import repository.InMemoryRepositoryCatalog;
import repository.InMemoryRepositoryGenre;
import repository.Repository;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ServiceGenre {
    private Repository<Integer, Genre> repo;
    private GenreValidator validator;

    public ServiceGenre() {}

    public ServiceGenre(Repository<Integer,Genre> repo, GenreValidator val){
        this.repo = repo;
        validator=val;
    }

    /**
     *
     * @param id
     * @param name
     * @throws ValidatorException
     */
    public void addGenre(Integer id, String name) throws ValidatorException {
        Genre new_genre = new Genre(name);
        new_genre.setId(id);
        validator.validate(new_genre);
        this.repo.save(new_genre);
    }

    /**
     *
     * @return all genres
     */
    public Iterable<Genre> getGenres(){
        return this.repo.findAll();
    }


    /**
     *
     * @param id of genre to be deleted
     * @throws IllegalArgumentException
     */
    public void deleteGenre(int id) throws IllegalArgumentException {
        repo.delete(id);
    }

    /**
     *
     * @param id
     * @param name = new name
     * @throws ValidatorException
     */
    public void updateGenre(int id, String name) throws ValidatorException {
        Genre genre = new Genre(name);
        genre.setId(id);
        validator.validate(genre);
        repo.update(genre);
    }

    /**
     *
     * @return all genres
     */
    public Iterable<Genre> getAllCinemas() { return repo.findAll(); }
}

