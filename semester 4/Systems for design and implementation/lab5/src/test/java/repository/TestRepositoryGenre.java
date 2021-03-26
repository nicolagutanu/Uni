package repository;

import domain.Genre;
import domain.Movie;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestRepositoryGenre {
    @Test
    public void testFindOne() throws Exception {
        Genre genre1=new Genre("a");
        genre1.setId(1);
        Genre genre2=new Genre("b");
        genre2.setId(2);
        InMemoryRepositoryGenre<Integer, Genre> repositoryGenre=new InMemoryRepositoryGenre<>();
        repositoryGenre.save(genre1);
        repositoryGenre.save(genre2);
        assert(repositoryGenre.findOne(2).get().getId().equals(2));
    }

    @Test
    public void testFindAll() throws Exception {
        Genre genre1=new Genre("a");
        genre1.setId(1);
        Genre genre2=new Genre("b");
        genre2.setId(2);
        InMemoryRepositoryGenre<Integer, Genre> repositoryGenre=new InMemoryRepositoryGenre<>();
        repositoryGenre.save(genre1);
        repositoryGenre.save(genre2);
        assert(repositoryGenre.findAll().spliterator().getExactSizeIfKnown()==2);
    }

    @Test
    public void testSave() throws Exception {
        Genre genre1=new Genre("a");
        genre1.setId(1);
        InMemoryRepositoryGenre<Integer, Genre> repositoryGenre=new InMemoryRepositoryGenre<>();
        repositoryGenre.save(genre1);
        assert(repositoryGenre.findOne(1).get().getId().equals(1));
    }

    @Test
    public void testSaveException() throws Exception {
        Genre genre1=null;
        InMemoryRepositoryGenre<Integer, Genre> repositoryGenre=new InMemoryRepositoryGenre<>();
        assertThrows(IllegalArgumentException.class, ()->repositoryGenre.save(genre1));
    }

    @Test
    public void testDelete() throws Exception {
        Genre genre1=new Genre("a");
        genre1.setId(1);
        InMemoryRepositoryGenre<Integer, Genre> repositoryGenre=new InMemoryRepositoryGenre<>();
        repositoryGenre.save(genre1);
        repositoryGenre.delete(1);
        assert(repositoryGenre.findAll().spliterator().getExactSizeIfKnown()==0);
    }

    @Test
    public void testUpdate() throws Exception {
        Genre genre1=new Genre("a");
        genre1.setId(1);
        InMemoryRepositoryGenre<Integer, Genre> repositoryGenre=new InMemoryRepositoryGenre<>();
        repositoryGenre.save(genre1);
        Genre genre2=new Genre("b");
        genre2.setId(1);
        repositoryGenre.update(genre2);
        assert(repositoryGenre.findOne(1).get().getName().equals("b"));
    }

    @Test
    public void testUpdateException() throws Exception {
        Genre genre1=null;
        InMemoryRepositoryGenre<Integer, Genre> repositoryGenre=new InMemoryRepositoryGenre<>();
        assertThrows(IllegalArgumentException.class, ()->repositoryGenre.update(genre1));
    }
}
