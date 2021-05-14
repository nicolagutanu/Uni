package ro.ubb.catalog.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.catalog.core.service.CatalogService;
import ro.ubb.catalog.web.converter.CatalogConverter;
import ro.ubb.catalog.web.dto.CatalogDto;
import ro.ubb.catalog.web.dto.CatalogsDto;

@RestController
public class CatalogController {
    public static final Logger log = LoggerFactory.getLogger(CatalogController.class);

    @Autowired
    private CatalogService catalogService;

    @Autowired
    private CatalogConverter catalogConverter;

    @RequestMapping(value="/get-all-catalogs")
    CatalogsDto getAllCatalogs() {
        log.trace("getAllCatalogs controller --- method entered");
        CatalogsDto result = new CatalogsDto(
                catalogConverter.convertModelsToDtos(
                        catalogService.getAllCatalog()));
        log.trace("getAllCatalogs --- method finished: result={}", result);
        return result;
    }

    @RequestMapping(value="/add-catalog", method= RequestMethod.POST)
    CatalogDto addCatalog(@RequestBody CatalogDto catalogDto) {
        log.trace("addCatalog controller --- method entered");
        var catalog = catalogConverter.convertDtoToModel(catalogDto);
        var result = catalogService.saveCatalog(catalog);
        var resultModel = catalogConverter.convertModelToDto(result);
        log.trace("addCatalog controller --- method finished: result={}", resultModel);
        return resultModel;
    }

    @RequestMapping(value="/update-catalog/{id}", method = RequestMethod.PUT)
    CatalogDto updateCatalog(@PathVariable Integer id, @RequestBody CatalogDto dto) {
        log.trace("updateCatalog controller --- method entered");
        CatalogDto result = catalogConverter.convertModelToDto(
                catalogService.updateCatalog(
                        catalogConverter.convertDtoToModel(dto)));
        log.trace("updateCatalog controller --- method finished: result={}", result);
        return result;
    }

    @RequestMapping(value="/delete-catalog/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteCatalog(@PathVariable Integer id) {
        log.trace("deleteCatalog controller --- method entered");
        catalogService.deleteCatalog(id);
        log.trace("deleteCatalog --- method finished");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
