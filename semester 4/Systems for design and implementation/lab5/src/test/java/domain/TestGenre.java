package domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestGenre {
    private static final Integer id = 1;
    private static final Integer new_id = 2;
    private static final String name = "tatbilb1";
    private static final String new_name = "tatbilb2";

    private Genre genre;

    @BeforeEach
    public void setUp() throws Exception {
        genre =new Genre(name);
        genre.setId(id);
    }

    @AfterEach
    public void tearDown() throws Exception {
        genre =null;
    }

    @Test
    public void testGetName() throws Exception {
        assert(name.equals(genre.getName()));
    }

    @Test
    public void testGetId() {
        assert(id.equals(genre.getId()));
    }

    @Test
    public void testSetName() throws Exception {
        genre.setName(new_name);
        assert(new_name.equals(genre.getName()));
    }

    @Test
    public void testSetId() {
        genre.setId(new_id);
        assert(new_id.equals(genre.getId()));
    }
}
