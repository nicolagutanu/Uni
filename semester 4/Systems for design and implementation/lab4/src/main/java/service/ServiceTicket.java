package service;

import domain.Cinema;
import domain.Movie;
import domain.Ticket;
import domain.validators.Validator;
import domain.validators.ValidatorException;
import repository.InMemoryRepositoryTicket;
import repository.Repository;

import java.util.Base64;

public class ServiceTicket {
    private Repository<Integer, Ticket> tickets;
    private Validator<Ticket> validator;


    public ServiceTicket(){}

    public ServiceTicket(Repository<Integer, Ticket> tickets, Validator<Ticket> validator) {
        this.tickets = tickets;
        this.validator = validator;
    }

    /**
     *
     * @param id
     * @param idMovie
     * @param idCinema
     * @param price
     * @throws ValidatorException
     */
    public void addTicket(int id, int idMovie, int idCinema, float price) throws ValidatorException {
        Ticket ticket = new Ticket(idMovie,idCinema,price);
        ticket.setId(id);
        validator.validate(ticket);
        tickets.save(ticket);
    }
    /**
     *
     * @param id
     * @throws IllegalArgumentException
     */
    public void deleteTicket(int id) throws IllegalArgumentException {
        tickets.delete(id);
    }

    /**
     *
     * @param id
     * @param idMovie
     * @param idCinema
     * @param price
     * @throws ValidatorException
     */
    public void updateTicket(int id, int idMovie, int idCinema, float price) throws ValidatorException {
        Ticket new_ticket =new Ticket(idMovie, idCinema, price);
        new_ticket.setId(id);
        validator.validate(new_ticket);
        tickets.update(new_ticket);
    }

    /**
     *
     * @return all cinemas
     */
    public Iterable<Ticket> getAllTickets() { return tickets.findAll(); }
}

