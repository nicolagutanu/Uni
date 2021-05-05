package ro.ubb.catalog.web.controller;

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
    @Autowired
    private CatalogService catalogService;

    @Autowired
    private CatalogConverter catalogConverter;

    @RequestMapping(value="/get-all-catalogs")
    CatalogsDto getAllCatalogs() {
        return new CatalogsDto(
                catalogConverter.convertModelsToDtos(
                        catalogService.getAllCatalog()));
    }

    @RequestMapping(value="/add-catalog", method= RequestMethod.POST)
    CatalogDto addCatalog(@RequestBody CatalogDto catalogDto) {
        var catalog = catalogConverter.convertDtoToModel(catalogDto);
        var result = catalogService.saveCatalog(catalog);
        var resultModel = catalogConverter.convertModelToDto(result);
        return resultModel;
    }

    @RequestMapping(value="/update-catalog/{id}", method = RequestMethod.PUT)
    CatalogDto updateCatalog(@PathVariable Integer id, @RequestBody CatalogDto dto) {
        return catalogConverter.convertModelToDto(
                catalogService.updateCatalog(
                        catalogConverter.convertDtoToModel(dto)));
    }

    @RequestMapping(value="/delete-catalog/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteCatalog(@PathVariable Integer id) {
        catalogService.deleteCatalog(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
