package ro.ubb.catalog.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.catalog.core.model.Catalog;
import ro.ubb.catalog.web.dto.CatalogDto;

@Component
public class CatalogConverter extends BaseConverter<Catalog, CatalogDto> {
    @Override
    public Catalog convertDtoToModel(CatalogDto dto) {
        var model = new Catalog();
        model.setId(dto.getId());
        model.setIdMovie(dto.getIdMovie());
        model.setIdGenre(dto.getIdGenre());
        return model;
    }

    @Override
    public CatalogDto convertModelToDto(Catalog catalog) {
        CatalogDto dto = new CatalogDto(catalog.getIdMovie(), catalog.getIdGenre());
        dto.setId(catalog.getId());
        return dto;
    }
}
