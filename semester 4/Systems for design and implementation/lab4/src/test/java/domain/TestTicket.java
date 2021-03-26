package domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestTicket {
    private static final Integer id = 1;
    private static final Integer new_id = 2;
    private static final Integer idMovie = 1;
    private static final Integer new_idMovie = 2;
    private static final Integer idCinema = 1;
    private static final Integer new_idCinema = 2;
    private static final Float price = 1.0f;
    private static final Float new_price = 2.0f;


    private Ticket ticket;

    @BeforeEach
    public void setUp() throws Exception {
        ticket =new Ticket(idMovie, idCinema, price);
        ticket.setId(id);
    }

    @AfterEach
    public void tearDown() throws Exception {
        ticket =null;
    }

    @Test
    public void testGetIdMovie() throws Exception {
        assert(idMovie.equals(ticket.getIdMovie()));
    }

    @Test
    public void testGetIdCinema() {
        assert(idCinema.equals(ticket.getIdCinema()));
    }

    @Test
    public void testGetPrice() {
        assert(price.equals(ticket.getPrice()));
    }

    @Test
    public void testGetId() {
        assert(id.equals(ticket.getId()));
    }

    @Test
    public void testSetIdMovie() throws Exception {
        ticket.setIdMovie(new_idMovie);
        assert(new_idMovie.equals(ticket.getIdMovie()));
    }

    @Test
    public void testSetIdCinema() {
        ticket.setIdCinema(new_idCinema);
        assert(new_idCinema.equals(ticket.getIdCinema()));
    }

    @Test
    public void testSetPrice() {
        ticket.setPrice(new_price);
        assert(new_price.equals(ticket.getPrice()));
    }

    @Test
    public void testSetId() {
        ticket.setId(new_id);
        assert(new_id.equals(ticket.getId()));
    }
}
