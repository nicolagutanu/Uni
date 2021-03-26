package domain.validators;

public interface Validator<T> {

    /**
     *
     * @param entity that needs to be validated
     * @throws ValidatorException
     */
    void validate(T entity) throws ValidatorException;
}
