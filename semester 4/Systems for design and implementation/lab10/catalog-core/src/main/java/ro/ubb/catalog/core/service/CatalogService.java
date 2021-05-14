package ro.ubb.catalog.core.service;

import ro.ubb.catalog.core.model.Catalog;

import java.util.List;

public interface CatalogService {
    List<Catalog> getAllCatalog();

    Catalog saveCatalog(Catalog catalog);

    Catalog updateCatalog(Catalog catalog);

    void deleteCatalog(Integer id);
}
