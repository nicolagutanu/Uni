package ro.ubb.springjpa.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.springjpa.model.Ticket;
import ro.ubb.springjpa.repository.TicketRepository;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {
    public static final Logger log = LoggerFactory.getLogger(TicketServiceImpl.class);

    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public List<Ticket> getAllTickets() {
        log.trace("getAllTickets --- method entered");

        List<Ticket> result = ticketRepository.findAll();

        log.trace("getAllTickets: result={}", result);

        return result;
    }

    @Override
    public void saveTicket(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    @Override
    @Transactional
    public void updateTicket(Ticket ticket) {
        log.trace("updateTicket - method entered: ticket={}", ticket);

        ticketRepository.findById(ticket.getId())
                .ifPresent(s -> {
                    s.setIdMovie(ticket.getIdMovie());
                    s.setIdCinema(ticket.getIdCinema());
                    s.setPrice(ticket.getPrice());
                    log.debug("updateTicket - updated: s={}", s);
                });

        log.trace("updateTicket - method finished");
    }

    @Override
    public void deleteTicketById(Integer id) {
        ticketRepository.deleteById(id);
    }
}
