package domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestCinema {
    private static final Integer id = 1;
    private static final Integer new_id = 2;
    private static final String name = "tatbilb1";
    private static final String new_name = "tatbilb2";
    private static final Integer nrOfSeats = 6;
    private static final Integer new_nrOfSeats = 7;

    private Cinema cinema;

    @BeforeEach
    public void setUp() throws Exception {
        cinema =new Cinema(name, nrOfSeats);
        cinema.setId(id);
    }

    @AfterEach
    public void tearDown() throws Exception {
        cinema =null;
    }

    @Test
    public void testGetName() throws Exception {
        assert(name.equals(cinema.getName()));
    }

    @Test
    public void testGetNrOfSeats() {
        assert(nrOfSeats.equals(cinema.getNrOfSeats()));
    }

    @Test
    public void testGetId() {
        assert(id.equals(cinema.getId()));
    }

    @Test
    public void testSetName() throws Exception {
        cinema.setName(new_name);
        assert(new_name.equals(cinema.getName()));
    }

    @Test
    public void testSetRating() {
        cinema.setNrOfSeats(new_nrOfSeats);
        assert(new_nrOfSeats.equals(cinema.getNrOfSeats()));
    }

    @Test
    public void testSetId() {
        cinema.setId(new_id);
        assert(new_id.equals(cinema.getId()));
    }
}
