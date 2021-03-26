package domain.validators;

import domain.Catalog;

import java.util.Optional;

public class CatalogValidator implements Validator<Catalog> {

    /**
     *
     * @param entity = catalog entry that needs to be validate
     * @throws ValidatorException
     */
    @Override
    public void validate(Catalog entity) throws ValidatorException {
        Optional.of(entity)
                .filter(item -> item.getId() > 0)
                .orElseThrow(() -> new ValidatorException("Invalid catalog id\n"));

        Optional.of(entity)
                .filter(item -> item.getIdMovie() > 0)
                .orElseThrow(() -> new ValidatorException("Invalid movie id\n"));

        Optional.of(entity)
                .filter(item -> item.getIdGenre() > 0)
                .orElseThrow(() -> new ValidatorException("Invalid genre id\n"));
    }
}
