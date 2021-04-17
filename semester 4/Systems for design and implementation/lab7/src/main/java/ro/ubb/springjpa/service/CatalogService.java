package ro.ubb.springjpa.service;

import ro.ubb.springjpa.model.Catalog;

import java.util.List;

public interface CatalogService {
    List<Catalog> getAllCatalog();

    void saveCatalog(Catalog catalog);

    void updateCatalog(Catalog catalog);

    void deleteCatalogById(Integer id);
}
