package domain.validators;

import domain.Ticket;

import java.util.Optional;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class TicketValidator implements Validator<Ticket> {

    /**
     *
     * @param entity = ticket entry that needs to be validated
     * @throws ValidatorException
     */
    @Override
    public void validate(Ticket entity) throws ValidatorException {
        Optional.of(entity)
                .filter(item -> item.getId() > 0)
                .orElseThrow(() -> new ValidatorException("Invalid ticket id\n"));

        Optional.of(entity)
                .filter(item -> item.getIdMovie() > 0)
                .orElseThrow(() -> new ValidatorException("Invalid movie id\n"));

        Optional.of(entity)
                .filter(item -> item.getIdCinema() > 0)
                .orElseThrow(() -> new ValidatorException("Invalid cinema id\n"));

        Optional.of(entity)
                .filter(item -> item.getPrice() > 0)
                .orElseThrow(() -> new ValidatorException("Invalid ticket price\n"));

    }

}
