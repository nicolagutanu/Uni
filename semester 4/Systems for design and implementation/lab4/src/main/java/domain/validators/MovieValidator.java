package domain.validators;

import domain.Movie;

import java.util.Optional;

public class MovieValidator implements Validator<Movie> {

    /**
     *
     * @param entity = movie that needs to be validated
     * @throws ValidatorException
     */
    @Override
    public void validate(Movie entity) throws ValidatorException {
        Optional.of(entity)
                .filter(item -> item.getId() > 0)
                .orElseThrow(() -> new ValidatorException("Invalid movie id\n"));

        Optional.of(entity)
                .filter(movie->movie.getRating()>0 && movie.getRating()<=10)
                .orElseThrow(()->new ValidatorException("Invalid movie rating\n"));

        Optional.of(entity)
                .filter(movie->movie.getName()!="")
                .orElseThrow(()->new ValidatorException("Invalid movie name\n"));
    }
}
