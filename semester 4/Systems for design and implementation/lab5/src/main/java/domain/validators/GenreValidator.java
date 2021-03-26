package domain.validators;

import domain.Genre;
import domain.validators.Validator;
import domain.validators.ValidatorException;

import java.util.Optional;

public class GenreValidator implements Validator<Genre> {

    /**
     *
     * @param entity = genre that needs to be validated
     * @throws ValidatorException
     *
     */
    @Override
    public void validate(Genre entity) throws ValidatorException {
        Optional.of(entity)
                .filter(item -> item.getId() > 0)
                .orElseThrow(() -> new ValidatorException("Invalid genre id\n"));


        Optional.of(entity)
                .filter(item -> item.getName() != "")
                .orElseThrow(()-> new ValidatorException("Invalid genre name\n"));

    }
}
