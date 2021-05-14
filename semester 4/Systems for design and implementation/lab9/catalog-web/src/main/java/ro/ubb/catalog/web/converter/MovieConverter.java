package ro.ubb.catalog.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.catalog.core.model.Movie;
import ro.ubb.catalog.web.dto.MovieDto;

@Component
public class MovieConverter extends BaseConverter<Movie, MovieDto> {
    @Override
    public Movie convertDtoToModel(MovieDto dto) {
        var model=new Movie();
        model.setId(dto.getId());
        model.setName(dto.getName());
        model.setRating(dto.getRating());
        return model;
    }

    @Override
    public MovieDto convertModelToDto(Movie movie) {
        MovieDto dto = new MovieDto(movie.getName(), movie.getRating());
        dto.setId(movie.getId());
        return dto;
    }
}
