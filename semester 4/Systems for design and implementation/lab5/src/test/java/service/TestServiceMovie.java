package service;

import domain.*;
import domain.validators.*;
import org.junit.jupiter.api.Test;
import repository.*;

public class TestServiceMovie {
    @Test
    public void testFilterMoviesByGenre() {
        InMemoryRepositoryMovie<Integer, Movie> repoMovie=new InMemoryRepositoryMovie<>();
        InMemoryRepositoryGenre<Integer, Genre> repoGenre=new InMemoryRepositoryGenre<>();
        InMemoryRepositoryCinema<Integer, Cinema> repoCinema = new InMemoryRepositoryCinema<>();
        InMemoryRepositoryCatalog<Integer, Catalog> repoCatalog = new InMemoryRepositoryCatalog<>();
        InMemoryRepositoryTicket<Integer, Ticket> repoTicket = new InMemoryRepositoryTicket<>();
        ServiceMovie serviceMovie=new ServiceMovie(repoMovie, repoCinema, repoGenre, repoCatalog, repoTicket, new MovieValidator());
        ServiceGenre serviceGenre=new ServiceGenre(repoGenre, new GenreValidator());
        ServiceCatalog serviceCatalog=new ServiceCatalog(repoCatalog, new CatalogValidator());

        serviceMovie.addMovie(1,"a", 1.0f);
        serviceMovie.addMovie(2,"b", 2.0f);
        serviceMovie.addMovie(3,"c", 3.0f);

        serviceGenre.addGenre(1, "x");
        serviceGenre.addGenre(2, "y");
        serviceGenre.addGenre(3, "z");

        serviceCatalog.addToCatalog(1,1,1);
        serviceCatalog.addToCatalog(2,1,2);
        serviceCatalog.addToCatalog(3, 2,1);
        serviceCatalog.addToCatalog(4, 3,2);
        serviceCatalog.addToCatalog(5, 3, 3);

        assert(serviceMovie.filterMoviesByGenre(3).size()==1);
    }

    @Test
    public void testFilterMoviesByCinema() {
        InMemoryRepositoryMovie<Integer, Movie> repoMovie=new InMemoryRepositoryMovie<>();
        InMemoryRepositoryGenre<Integer, Genre> repoGenre=new InMemoryRepositoryGenre<>();
        InMemoryRepositoryCinema<Integer, Cinema> repoCinema = new InMemoryRepositoryCinema<>();
        InMemoryRepositoryCatalog<Integer, Catalog> repoCatalog = new InMemoryRepositoryCatalog<>();
        InMemoryRepositoryTicket<Integer, Ticket> repoTicket = new InMemoryRepositoryTicket<>();
        ServiceMovie serviceMovie=new ServiceMovie(repoMovie, repoCinema, repoGenre, repoCatalog, repoTicket, new MovieValidator());
        ServiceCinema serviceCinema=new ServiceCinema(repoCinema, new CinemaValidator());
        ServiceTicket serviceTicket=new ServiceTicket(repoTicket, new TicketValidator());


        serviceMovie.addMovie(1,"a", 1.0f);
        serviceMovie.addMovie(2,"b", 2.0f);
        serviceMovie.addMovie(3,"c", 3.0f);

        serviceCinema.addCinema(1, "x", 1);
        serviceCinema.addCinema(2, "y", 2);
        serviceCinema.addCinema(3, "z", 3);

        serviceTicket.addTicket(1, 1, 1, 1.0f);
        serviceTicket.addTicket(2, 1, 3, 2.0f);
        serviceTicket.addTicket(3, 2, 1, 3.0f);
        serviceTicket.addTicket(4, 2, 2, 4.0f);
        serviceTicket.addTicket(5, 3, 1, 5.0f);

        assert(serviceMovie.filterMovieByCinema(1).size()==3);
    }

    @Test
    public void testFilterGenreByMovie() {
        InMemoryRepositoryMovie<Integer, Movie> repoMovie=new InMemoryRepositoryMovie<>();
        InMemoryRepositoryGenre<Integer, Genre> repoGenre=new InMemoryRepositoryGenre<>();
        InMemoryRepositoryCinema<Integer, Cinema> repoCinema = new InMemoryRepositoryCinema<>();
        InMemoryRepositoryCatalog<Integer, Catalog> repoCatalog = new InMemoryRepositoryCatalog<>();
        InMemoryRepositoryTicket<Integer, Ticket> repoTicket = new InMemoryRepositoryTicket<>();
        ServiceMovie serviceMovie=new ServiceMovie(repoMovie, repoCinema, repoGenre, repoCatalog, repoTicket, new MovieValidator());
        ServiceGenre serviceGenre=new ServiceGenre(repoGenre, new GenreValidator());
        ServiceCatalog serviceCatalog=new ServiceCatalog(repoCatalog, new CatalogValidator());

        serviceMovie.addMovie(1,"a", 1.0f);
        serviceMovie.addMovie(2,"b", 2.0f);
        serviceMovie.addMovie(3,"c", 3.0f);

        serviceGenre.addGenre(1, "x");
        serviceGenre.addGenre(2, "y");
        serviceGenre.addGenre(3, "z");

        serviceCatalog.addToCatalog(1,1,1);
        serviceCatalog.addToCatalog(2,1,2);
        serviceCatalog.addToCatalog(3, 2,1);
        serviceCatalog.addToCatalog(4, 3,2);
        serviceCatalog.addToCatalog(5, 3, 3);

        assert(serviceMovie.filterGenresByMovie(2).size()==1);
    }

    @Test
    public void testFilterCinemasByMovie() {
        InMemoryRepositoryMovie<Integer, Movie> repoMovie=new InMemoryRepositoryMovie<>();
        InMemoryRepositoryGenre<Integer, Genre> repoGenre=new InMemoryRepositoryGenre<>();
        InMemoryRepositoryCinema<Integer, Cinema> repoCinema = new InMemoryRepositoryCinema<>();
        InMemoryRepositoryCatalog<Integer, Catalog> repoCatalog = new InMemoryRepositoryCatalog<>();
        InMemoryRepositoryTicket<Integer, Ticket> repoTicket = new InMemoryRepositoryTicket<>();
        ServiceMovie serviceMovie=new ServiceMovie(repoMovie, repoCinema, repoGenre, repoCatalog, repoTicket, new MovieValidator());
        ServiceCinema serviceCinema=new ServiceCinema(repoCinema, new CinemaValidator());
        ServiceTicket serviceTicket=new ServiceTicket(repoTicket, new TicketValidator());


        serviceMovie.addMovie(1,"a", 1.0f);
        serviceMovie.addMovie(2,"b", 2.0f);
        serviceMovie.addMovie(3,"c", 3.0f);

        serviceCinema.addCinema(1, "x", 1);
        serviceCinema.addCinema(2, "y", 2);
        serviceCinema.addCinema(3, "z", 3);

        serviceTicket.addTicket(1, 1, 1, 1.0f);
        serviceTicket.addTicket(2, 1, 3, 2.0f);
        serviceTicket.addTicket(3, 2, 1, 3.0f);
        serviceTicket.addTicket(4, 2, 2, 4.0f);
        serviceTicket.addTicket(5, 3, 1, 5.0f);

        assert(serviceMovie.filterCinemaByMovie(1).size()==2);
    }
}
