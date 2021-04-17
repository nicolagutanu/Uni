package ro.ubb.springjpa.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.springjpa.model.Catalog;
import ro.ubb.springjpa.repository.CatalogRepository;

import java.util.List;

@Service
public class CatalogServiceImpl implements CatalogService {
    public static final Logger log = LoggerFactory.getLogger(CatalogServiceImpl.class);

    @Autowired
    private CatalogRepository catalogRepository;

    @Override
    public List<Catalog> getAllCatalog() {
        log.trace("getAllCatalog --- method entered");

        List<Catalog> result = catalogRepository.findAll();

        log.trace("getAllCatalog: result={}", result);

        return result;
    }

    @Override
    public void saveCatalog(Catalog catalog) {
        catalogRepository.save(catalog);
    }

    @Override
    @Transactional
    public void updateCatalog(Catalog catalog) {
        log.trace("updateCatalog - method entered: catalog={}", catalog);

        catalogRepository.findById(catalog.getId())
                .ifPresent(s -> {
                    s.setIdMovie(catalog.getIdMovie());
                    s.setIdGenre(catalog.getIdGenre());
                    log.debug("updateCatalog - updated: s={}", s);
                });

        log.trace("updateCatalog - method finished");
    }

    @Override
    public void deleteCatalogById(Integer id) {
        catalogRepository.deleteById(id);
    }
}
