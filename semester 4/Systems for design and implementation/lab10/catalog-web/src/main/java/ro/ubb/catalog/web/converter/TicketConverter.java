package ro.ubb.catalog.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.catalog.core.model.Ticket;
import ro.ubb.catalog.web.dto.TicketDto;

@Component
public class TicketConverter extends BaseConverter<Ticket, TicketDto> {
    @Override
    public Ticket convertDtoToModel(TicketDto dto) {
        var model = new Ticket();
        model.setId(dto.getId());
        model.setIdMovie(dto.getIdMovie());
        model.setIdCinema(dto.getIdCinema());
        model.setPrice(dto.getPrice());
        return model;
    }

    @Override
    public TicketDto convertModelToDto(Ticket ticket) {
        TicketDto dto = new TicketDto(ticket.getIdMovie(), ticket.getIdCinema(), ticket.getPrice());
        dto.setId(ticket.getId());
        return dto;
    }
}
