package ro.ubb.catalog.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.catalog.core.model.Catalog;
import ro.ubb.catalog.core.repository.MyCatalogRepository;

import java.util.List;

@Service
public class CatalogServiceImpl implements CatalogService {
    public static final Logger log = LoggerFactory.getLogger(CatalogServiceImpl.class);

    @Autowired
    private MyCatalogRepository catalogRepository;

    @Override
    public List<Catalog> getAllCatalog() {
        log.trace("getAllCatalog  --- method entered");
        List<Catalog> catalog = catalogRepository.findAll();
        log.trace("getAllCatalog --- method finished: result={}", catalog);
        return catalog;
    }

    @Override
    public Catalog saveCatalog(Catalog catalog) {
        log.trace("saveCatalog --- method entered");
        Catalog result = catalogRepository.save(catalog);
        log.trace("saveCatalog --- method finshed: result={}", result);
        return result;
    }

    @Override
    @Transactional
    public Catalog updateCatalog(Catalog catalog) {
        log.trace("updateCatalog --- method entered");
        Catalog updateCatalog = catalogRepository.findById(catalog.getId()).orElseThrow();
        updateCatalog.setIdMovie(catalog.getIdMovie());
        updateCatalog.setIdGenre(catalog.getIdGenre());
        log.trace("updateCatalog --- method finished: result={}", updateCatalog);
        return catalog;
    }

    @Override
    public void deleteCatalog(Integer id) {
        log.trace("deleteCatalog --- method entered");
        catalogRepository.deleteById(id);
        log.trace("deleteCatalog --- method finished");
    }
}
