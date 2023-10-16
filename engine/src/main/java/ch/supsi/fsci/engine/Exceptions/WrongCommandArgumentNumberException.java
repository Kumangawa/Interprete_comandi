package ch.supsi.fsci.engine.Exceptions;

public class WrongCommandArgumentNumberException extends RuntimeException {
    public WrongCommandArgumentNumberException(final String message) {
        super(message);

    }
}
