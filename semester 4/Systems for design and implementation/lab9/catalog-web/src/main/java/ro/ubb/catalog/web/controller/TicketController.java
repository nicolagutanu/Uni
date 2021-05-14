package ro.ubb.catalog.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.catalog.core.service.TicketService;
import ro.ubb.catalog.web.converter.TicketConverter;
import ro.ubb.catalog.web.dto.TicketDto;
import ro.ubb.catalog.web.dto.TicketsDto;

@RestController
public class TicketController {
    public static final Logger log = LoggerFactory.getLogger(TicketController.class);

    @Autowired
    private TicketService ticketService;

    @Autowired
    private TicketConverter ticketConverter;

    @RequestMapping(value="/get-all-tickets")
    TicketsDto getAllTickets() {
        log.trace("getAllTickets controller --- method entered");
        TicketsDto result =  new TicketsDto(
                ticketConverter.convertModelsToDtos(
                        ticketService.getAllTickets()));
        log.trace("getAllTickets controller --- method finished: result={}", result);
        return result;
    }

    @RequestMapping(value="/add-ticket", method= RequestMethod.POST)
    TicketDto addTicket(@RequestBody TicketDto ticketDto) {
        log.trace("addTicket controller --- method entered");
        var ticket = ticketConverter.convertDtoToModel(ticketDto);
        var result = ticketService.saveTicket(ticket);
        var resultModel = ticketConverter.convertModelToDto(result);
        log.trace("addTicket controller ---  method finished: result={}", resultModel);
        return resultModel;
    }

    @RequestMapping(value="/update-ticket/{id}", method = RequestMethod.PUT)
    TicketDto updateTicket(@PathVariable Integer id, @RequestBody TicketDto dto) {
        log.trace("updateTicket controller --- method entered");
        TicketDto result =  ticketConverter.convertModelToDto(
                ticketService.updateTicket(
                        ticketConverter.convertDtoToModel(dto)));
        log.trace("updateTicket controller --- method finished: result={}", result);
        return result;
    }

    @RequestMapping(value="/delete-ticket/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteTicket(@PathVariable Integer id) {
        log.trace("deleteTicket controller --- method entered");
        ticketService.deleteTicket(id);
        log.trace("deleteTicket controller --- method finished");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
