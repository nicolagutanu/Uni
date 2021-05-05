package ro.ubb.catalog.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.catalog.core.model.Ticket;
import ro.ubb.catalog.core.repository.TicketRepository;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket saveTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    @Transactional
    public Ticket updateTicket(Ticket ticket) {
        Ticket updateTicket = ticketRepository.findById(ticket.getId()).orElseThrow();
        updateTicket.setIdMovie(ticket.getIdMovie());
        updateTicket.setIdCinema(ticket.getIdCinema());
        updateTicket.setPrice(ticket.getPrice());
        return ticket;
    }

    @Override
    public void deleteTicket(Integer id) {
        ticketRepository.deleteById(id);
    }
}
