package service;

import domain.Cinema;
import domain.validators.Validator;
import domain.validators.ValidatorException;
import repository.InMemoryRepositoryCinema;
import repository.Repository;

public class ServiceCinema {
    private Repository<Integer, Cinema> cinemas;
    Validator<Cinema> validator;

    public ServiceCinema(Repository<Integer, Cinema> cinemas, Validator<Cinema> validator) {
        this.cinemas = cinemas;
        this.validator = validator;
    }

    /**
     *
     * @param id
     * @param name
     * @param nrOfSeats
     * @throws ValidatorException
     */
    public void addCinema(int id, String name, Integer nrOfSeats) throws ValidatorException {
        Cinema cinema=new Cinema(name, nrOfSeats);
        cinema.setId(id);
        validator.validate(cinema);
        cinemas.save(cinema);
    }

    /**
     *
     * @param id
     * @throws IllegalArgumentException
     */
    public void deleteCinema(int id) throws IllegalArgumentException {
        cinemas.delete(id);
    }

    /**
     *
     * @param id
     * @param name
     * @param nrOfSeats
     * @throws ValidatorException
     */
    public void updateCinema(int id, String name, Integer nrOfSeats) throws ValidatorException {
        Cinema cinema=new Cinema(name, nrOfSeats);
        cinema.setId(id);
        validator.validate(cinema);
        cinemas.update(cinema);
    }

    /**
     *
     * @return all cinemas
     */
    public Iterable<Cinema> getAllCinemas() { return cinemas.findAll(); }
}
