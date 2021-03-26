package domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestCatalog {
    private static final Integer id = 1;
    private static final Integer new_id = 2;
    private static final Integer idMovie = 1;
    private static final Integer new_idMovie = 2;
    private static final Integer idGenre = 1;
    private static final Integer new_idGenre = 2;


    private Catalog catalog;

    @BeforeEach
    public void setUp() throws Exception {
        catalog =new Catalog(idMovie, idGenre);
        catalog.setId(id);
    }

    @AfterEach
    public void tearDown() throws Exception {
        catalog =null;
    }

    @Test
    public void testGetIdMovie() throws Exception {
        assert(idMovie.equals(catalog.getIdMovie()));
    }

    @Test
    public void testGetIdGenre() {
        assert(idGenre.equals(catalog.getIdGenre()));
    }

    @Test
    public void testGetId() {
        assert(id.equals(catalog.getId()));
    }

    @Test
    public void testSetIdMovie() throws Exception {
        catalog.setIdMovie(new_idMovie);
        assert(new_idMovie.equals(catalog.getIdMovie()));
    }

    @Test
    public void testSetIdGenre() {
        catalog.setIdGenre(new_idGenre);
        assert(new_idGenre.equals(catalog.getIdGenre()));
    }

    @Test
    public void testSetId() {
        catalog.setId(new_id);
        assert(new_id.equals(catalog.getId()));
    }
}
