package ro.ubb.catalog.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.catalog.core.service.TicketService;
import ro.ubb.catalog.web.converter.TicketConverter;
import ro.ubb.catalog.web.dto.MovieDto;
import ro.ubb.catalog.web.dto.MoviesDto;
import ro.ubb.catalog.web.dto.TicketDto;
import ro.ubb.catalog.web.dto.TicketsDto;

@RestController
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @Autowired
    private TicketConverter ticketConverter;

    @RequestMapping(value="/get-all-tickets")
    TicketsDto getAllTickets() {
        return new TicketsDto(
                ticketConverter.convertModelsToDtos(
                        ticketService.getAllTickets()));
    }

    @RequestMapping(value="/add-ticket", method= RequestMethod.POST)
    TicketDto addTicket(@RequestBody TicketDto ticketDto) {
        var ticket = ticketConverter.convertDtoToModel(ticketDto);
        var result = ticketService.saveTicket(ticket);
        var resultModel = ticketConverter.convertModelToDto(result);
        return resultModel;
    }

    @RequestMapping(value="/update-ticket/{id}", method = RequestMethod.PUT)
    TicketDto updateTicket(@PathVariable Integer id, @RequestBody TicketDto dto) {
        return ticketConverter.convertModelToDto(
                ticketService.updateTicket(
                        ticketConverter.convertDtoToModel(dto)));
    }

    @RequestMapping(value="/delete-ticket/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteTicket(@PathVariable Integer id) {
        ticketService.deleteTicket(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
