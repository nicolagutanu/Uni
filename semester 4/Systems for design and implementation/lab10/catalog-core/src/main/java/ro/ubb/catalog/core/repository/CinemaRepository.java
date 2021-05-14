package ro.ubb.catalog.core.repository;

import org.springframework.data.jpa.repository.Query;
import ro.ubb.catalog.core.model.Cinema;

import java.util.List;

public interface CinemaRepository extends CatalogRepository<Cinema, Integer> {
    @Query(value = "select * from Cinema c where c.nrOfSeats >= ?1", nativeQuery = true)
    List<Cinema> getCinemasByNrOfSeats(Integer nrOfSeats);

    @Query(value = "select * from Cinema order by nrOfSeats", nativeQuery = true)
    List<Cinema> sortCinemasByNrOfSeats();
}
