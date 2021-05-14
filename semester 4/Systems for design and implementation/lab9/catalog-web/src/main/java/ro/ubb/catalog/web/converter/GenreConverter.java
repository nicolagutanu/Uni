package ro.ubb.catalog.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.catalog.core.model.Genre;
import ro.ubb.catalog.web.dto.GenreDto;

@Component
public class GenreConverter extends BaseConverter<Genre, GenreDto> {
    @Override
    public Genre convertDtoToModel(GenreDto dto) {
        var model = new Genre();
        model.setId(dto.getId());
        model.setName(dto.getName());
        return model;
    }

    @Override
    public GenreDto convertModelToDto(Genre genre) {
        GenreDto dto = new GenreDto(genre.getName());
        dto.setId(genre.getId());
        return dto;
    }
}
