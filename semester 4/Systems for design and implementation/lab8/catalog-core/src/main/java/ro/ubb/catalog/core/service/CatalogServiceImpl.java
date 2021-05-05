package ro.ubb.catalog.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.catalog.core.model.Catalog;
import ro.ubb.catalog.core.repository.MyCatalogRepository;

import java.util.List;

@Service
public class CatalogServiceImpl implements CatalogService {
    @Autowired
    private MyCatalogRepository catalogRepository;

    @Override
    public List<Catalog> getAllCatalog() {
        return catalogRepository.findAll();
    }

    @Override
    public Catalog saveCatalog(Catalog catalog) {
        return catalogRepository.save(catalog);
    }

    @Override
    @Transactional
    public Catalog updateCatalog(Catalog catalog) {
        Catalog updateCatalog = catalogRepository.findById(catalog.getId()).orElseThrow();
        updateCatalog.setIdMovie(catalog.getIdMovie());
        updateCatalog.setIdGenre(catalog.getIdGenre());
        return catalog;
    }

    @Override
    public void deleteCatalog(Integer id) {
        catalogRepository.deleteById(id);
    }
}
