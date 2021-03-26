package service;

import domain.Catalog;
import domain.validators.Validator;
import domain.validators.ValidatorException;
import repository.InMemoryRepositoryCatalog;
import repository.Repository;

public class ServiceCatalog {
    private Repository<Integer, Catalog> catalog;
    Validator<Catalog> validator;

    public ServiceCatalog() {}

    public ServiceCatalog(Repository<Integer, Catalog> c, Validator<Catalog> val) {
        catalog=c;
        validator=val;
    }

    /**
     *
     * @param id
     * @param idMovie
     * @param idGenre
     * @throws ValidatorException
     */
    public void addToCatalog(int id, int idMovie, int idGenre) throws ValidatorException {
        Catalog cat=new Catalog(idMovie, idGenre);
        cat.setId(id);
        validator.validate(cat);
        catalog.save(cat);
    }

    /**
     *
     * @param id
     * @throws IllegalArgumentException
     */
    public void deleteFromCatalog(int id) throws IllegalArgumentException {
        catalog.delete(id);
    }

    /**
     *
     * @param id
     * @param idMovie
     * @param idGenre
     * @throws ValidatorException
     */
    public void updateCatalog(int id, int idMovie, int idGenre) throws ValidatorException {
        Catalog cat=new Catalog(idMovie, idGenre);
        cat.setId(id);
        validator.validate(cat);
        catalog.update(cat);
    }

    /**
     *
     * @return genre-movie catalog
     */
    public Iterable<Catalog> getCatalog() { return catalog.findAll(); }
}
