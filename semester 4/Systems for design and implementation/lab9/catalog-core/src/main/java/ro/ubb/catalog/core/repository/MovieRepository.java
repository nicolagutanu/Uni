package ro.ubb.catalog.core.repository;

import org.springframework.data.jpa.repository.Query;
import ro.ubb.catalog.core.model.Movie;

import java.util.List;

public interface MovieRepository extends CatalogRepository<Movie, Integer> {
    @Query(value = "select * from Movie m where m.rating >= ?1", nativeQuery = true)
    List<Movie> getMoviesByRating(Float rating);
}
