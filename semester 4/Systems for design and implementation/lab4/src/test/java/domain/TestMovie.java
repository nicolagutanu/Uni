package domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestMovie {
    private static final Integer id = 1;
    private static final Integer new_id = 2;
    private static final String name = "tatbilb1";
    private static final String new_name = "tatbilb2";
    private static final Float rating = 6.7f;
    private static final Float new_rating = 7.2f;

    private Movie movie;

    @BeforeEach
    public void setUp() throws Exception {
        movie=new Movie(name, rating);
        movie.setId(id);
    }

    @AfterEach
    public void tearDown() throws Exception {
        movie=null;
    }

    @Test
    public void testGetName() throws Exception {
        assert(name.equals(movie.getName()));
    }

    @Test
    public void testGetRating() {
        assert(rating.equals(movie.getRating()));
    }

    @Test
    public void testGetId() {
        assert(id.equals(movie.getId()));
    }

    @Test
    public void testSetName() throws Exception {
        movie.setName(new_name);
        assert(new_name.equals(movie.getName()));
    }

    @Test
    public void testSetRating() {
        movie.setRating(new_rating);
        assert(new_rating.equals(movie.getRating()));
    }

    @Test
    public void testSetId() {
        movie.setId(new_id);
        assert(new_id.equals(movie.getId()));
    }
}
