package service;

import domain.*;
import domain.validators.ValidatorException;
import domain.validators.Validator;
import repository.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ServiceMovie {
    private Repository<Integer, Movie> movies;
    private Repository<Integer, Cinema> cinemas;
    private Repository<Integer, Genre> genres;
    private Repository<Integer, Ticket> tickets;
    private Repository<Integer, Catalog> catalog;
    Validator<Movie> validator;

    public ServiceMovie() {}

    public ServiceMovie(Repository<Integer, Movie> m, Repository<Integer, Cinema> cin, Repository<Integer, Genre> g, Repository<Integer, Catalog> c, Repository<Integer,Ticket> ticket, Validator<Movie> val) {
        movies=m;
        cinemas=cin;
        genres=g;
        catalog=c;
        tickets = ticket;
        validator=val;
    }

    /**
     *
     * @param id
     * @param name
     * @param rating
     * @throws ValidatorException
     */
    public void addMovie(int id, String name, Float rating) throws ValidatorException {
        Movie movie=new Movie(name, rating);
        movie.setId(id);
        validator.validate(movie);
        movies.save(movie);
    }

    /**
     *
     * @param id
     * @throws IllegalArgumentException
     */
    public void deleteMovie(int id) throws IllegalArgumentException {
        movies.delete(id);
    }

    /**
     *
     * @param id
     * @param name
     * @param rating
     * @throws ValidatorException
     */
    public void updateMovie(int id, String name, Float rating) throws ValidatorException {
        Movie movie=new Movie(name, rating);
        movie.setId(id);
        validator.validate(movie);
        movies.update(movie);
    }

    /**
     *
     * @param idGenre
     * @return filtered movies by given idGenre
     */
    public Set<Movie> filterMoviesByGenre(int idGenre) {
        Set<Integer> filteredCatalog=StreamSupport.stream(catalog.findAll().spliterator(), false)
                .filter(entry->entry.getIdGenre()==idGenre)
                .map(entry->entry.getIdMovie())
                .collect(Collectors.toSet());

        Iterable<Movie> allMovies = movies.findAll();
        Set<Movie> filteredMovies=new HashSet<>();
        allMovies.forEach(filteredMovies::add);
        filteredMovies.removeIf(movie->!filteredCatalog.contains(movie.getId()));
        return filteredMovies;
    }

    /**
     *
     * @param idMovie
     * @return filtered genres by given idMovie
     */
    public Set<Genre> filterGenresByMovie(int idMovie) {
        Set<Integer> filteredCatalog=StreamSupport.stream(catalog.findAll().spliterator(), false)
                .filter(entry->entry.getIdMovie()==idMovie)
                .map(entry->entry.getIdGenre())
                .collect(Collectors.toSet());

        Iterable<Genre> allGenres = genres.findAll();
        Set<Genre> filteredGenres=new HashSet<>();
        allGenres.forEach(filteredGenres::add);
        filteredGenres.removeIf(genre->!filteredCatalog.contains(genre.getId()));
        return filteredGenres;
    }

    /**
     *
     * @param idCinema
     * @return filtered movies by cinema
     * Shows all movies from a certain cinema
     */
    public Set<Movie> filterMovieByCinema(int idCinema){
        Set<Integer> filteredCinema = StreamSupport.stream(tickets.
                findAll().spliterator(), false)
                .filter(entry -> entry.getIdCinema() == idCinema)
                .map(entry->entry.getIdMovie())
                .collect(Collectors.toSet());

        Iterable<Movie> all = movies.findAll();
        Set<Movie> filtered = new HashSet<>();
        all.forEach(filtered::add);
        filtered.removeIf(movie->!filteredCinema.contains(movie.getId()));
        return filtered;
    }

    /**
     *
     * @param idMovie
     * @return filtered cinemas by movie
     * Shows all the cinemas in which a particular movie is screening
     */
    public Set<Cinema> filterCinemaByMovie(int idMovie) {
        Set<Integer> filteredCinema = StreamSupport.stream(tickets.findAll().spliterator(), false)
                .filter(entry -> entry.getIdMovie() == idMovie)
                .map(entry -> entry.getIdCinema())
                .collect(Collectors.toSet());

        Iterable<Cinema> all = cinemas.findAll();
        Set<Cinema> filtered = new HashSet<>();
        all.forEach(filtered::add);
        filtered.removeIf(cinema->!filteredCinema.contains(cinema.getId()));
        return filtered;
    }

    /**
     *
     * @return all movies
     */
    public Iterable<Movie> getAllMovies() { return movies.findAll(); }
}
