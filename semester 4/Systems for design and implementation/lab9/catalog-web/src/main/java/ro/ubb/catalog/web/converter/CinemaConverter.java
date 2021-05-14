package ro.ubb.catalog.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.catalog.core.model.Cinema;
import ro.ubb.catalog.web.dto.CinemaDto;

@Component
public class CinemaConverter extends BaseConverter<Cinema, CinemaDto> {
    @Override
    public Cinema convertDtoToModel(CinemaDto dto) {
        var model = new Cinema();
        model.setId(dto.getId());
        model.setName(dto.getName());
        model.setNrOfSeats(dto.getNrOfSeats());
        return model;
    }

    @Override
    public CinemaDto convertModelToDto(Cinema cinema) {
        CinemaDto dto = new CinemaDto(cinema.getName(), cinema.getNrOfSeats());
        dto.setId(cinema.getId());
        return dto;
    }
}
