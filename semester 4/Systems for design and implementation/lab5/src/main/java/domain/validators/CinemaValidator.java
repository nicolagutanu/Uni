package domain.validators;

import domain.Cinema;

import java.util.Optional;

public class CinemaValidator implements Validator<Cinema>{

    /**
     *
     * @param entity = cinema that needs to be validated
     * @throws ValidatorException
     */
    @Override
    public void validate(Cinema entity) throws ValidatorException {
        Optional.of(entity)
                .filter(item -> item.getId() > 0)
                .orElseThrow(() -> new ValidatorException("Invalid cinema id\n"));

        Optional.of(entity)
                .filter(item -> item.getName() != "")
                .orElseThrow(()-> new ValidatorException("Invalid cinema name!\n"));

        Optional.of(entity)
                .filter(item -> item.getNrOfSeats()>0)
                .orElseThrow(()->new ValidatorException("Invalid number of seats!\n"));
    }
}
