package domain.validators;

public class MovieException extends RuntimeException {
    public MovieException(String message) {
        super(message);
    }

    public MovieException(String message, Throwable cause) {
        super(message, cause);
    }

    public MovieException(Throwable cause) {
        super(cause);
    }
}
