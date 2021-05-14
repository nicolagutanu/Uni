package ro.ubb.catalog.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.catalog.core.model.Ticket;
import ro.ubb.catalog.core.repository.TicketRepository;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {
    public static final Logger log = LoggerFactory.getLogger(TicketServiceImpl.class);

    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public List<Ticket> getAllTickets() {
        log.trace("getAllTickets --- method entered");
        List<Ticket> tickets = ticketRepository.findAll();
        log.trace("getAllTickets ---  method finished: result={}", tickets);
        return tickets;
    }

    @Override
    public Ticket saveTicket(Ticket ticket) {
        log.trace("saveTicket --- method entered");
        Ticket result = ticketRepository.save(ticket);
        log.trace("saveTicket --- method finished: result={}", ticket);
        return result;
    }

    @Override
    @Transactional
    public Ticket updateTicket(Ticket ticket) {
        log.trace("updateTicket --- method entered");
        Ticket updateTicket = ticketRepository.findById(ticket.getId()).orElseThrow();
        updateTicket.setIdMovie(ticket.getIdMovie());
        updateTicket.setIdCinema(ticket.getIdCinema());
        updateTicket.setPrice(ticket.getPrice());
        log.trace("updateTicket --- method finished: result={}", updateTicket);
        return ticket;
    }

    @Override
    public void deleteTicket(Integer id) {
        log.trace("deleteTicket --- method entered");
        ticketRepository.deleteById(id);
        log.trace("deleteTicket --- method finished");
    }
}
