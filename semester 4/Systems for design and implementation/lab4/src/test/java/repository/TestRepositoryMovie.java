package repository;

import domain.Movie;
import domain.validators.ValidatorException;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public class TestRepositoryMovie {
    @Test
    public void testFindOne() throws Exception {
        Movie movie1=new Movie("a", 1.0f);
        movie1.setId(1);
        Movie movie2=new Movie("b", 2.0f);
        movie2.setId(2);
        InMemoryRepositoryMovie<Integer, Movie> repoMovie=new InMemoryRepositoryMovie<>();
        repoMovie.save(movie1);
        repoMovie.save(movie2);
        assert(repoMovie.findOne(2).get().getId().equals(2));
    }

    @Test
    public void testFindAll() throws Exception {
        Movie movie1=new Movie("a", 1.0f);
        movie1.setId(1);
        Movie movie2=new Movie("b", 2.0f);
        movie2.setId(2);
        InMemoryRepositoryMovie<Integer, Movie> repoMovie=new InMemoryRepositoryMovie<>();
        repoMovie.save(movie1);
        repoMovie.save(movie2);
        assert(repoMovie.findAll().spliterator().getExactSizeIfKnown()==2);
    }

    @Test
    public void testSave() throws Exception {
        Movie movie1=new Movie("a", 1.0f);
        movie1.setId(1);
        InMemoryRepositoryMovie<Integer, Movie> repoMovie=new InMemoryRepositoryMovie<>();
        repoMovie.save(movie1);
        assert(repoMovie.findOne(1).get().getId().equals(1));
    }

    @Test
    public void testSaveException() throws Exception {
        Movie movie1=null;
        InMemoryRepositoryMovie<Integer, Movie> repoMovie=new InMemoryRepositoryMovie<>();
        assertThrows(IllegalArgumentException.class, ()->repoMovie.save(movie1));
    }

    @Test
    public void testDelete() throws Exception {
        Movie movie1=new Movie("a", 1.0f);
        movie1.setId(1);
        InMemoryRepositoryMovie<Integer, Movie> repoMovie=new InMemoryRepositoryMovie<>();
        repoMovie.save(movie1);
        repoMovie.delete(1);
        assert(repoMovie.findAll().spliterator().getExactSizeIfKnown()==0);
    }

    @Test
    public void testUpdate() throws Exception {
        Movie movie1=new Movie("a", 1.0f);
        movie1.setId(1);
        InMemoryRepositoryMovie<Integer, Movie> repoMovie=new InMemoryRepositoryMovie<>();
        repoMovie.save(movie1);
        Movie movie2=new Movie("b", 2.0f);
        movie2.setId(1);
        repoMovie.update(movie2);
        assert(repoMovie.findOne(1).get().getName().equals("b"));
    }

    @Test
    public void testUpdateException() throws Exception {
        Movie movie1=null;
        InMemoryRepositoryMovie<Integer, Movie> repoMovie=new InMemoryRepositoryMovie<>();
        assertThrows(IllegalArgumentException.class, ()->repoMovie.update(movie1));
    }
}
